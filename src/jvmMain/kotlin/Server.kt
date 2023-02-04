import ggj.Parcel
import ggj.User
import ggj.UserMe
import ggj.UserSession
import ggj.dao.ParcelDao
import ggj.dao.Parcels
import ggj.dao.UserDao
import ggj.dao.Users
import ggj.task.UpdateWorldJob
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.EqOp
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun getParcelFromSession(userSession:UserSession?): Parcel? {
    var parcel: Parcel? = null
    transaction {
        parcel = Parcels // todo use dao
            .select(
                Parcels.user eq userSession!!.userId
            )
            .map { resultRow ->
                Parcels.mapToObject(resultRow)
                //UserMe(User(resultRow[Users.id].value, resultRow[Users.name]), resultRow[Users.tag])
            }
            .singleOrNull()

    }
    return parcel
}
fun Application.module() {
    val env = System.getenv()
    val dbHost: String = env.getOrDefault("POSTGRESQL_ADDON_HOST", "localhost")
    val dbPort: String = env.getOrDefault("POSTGRESQL_ADDON_PORT", "5432")
    val dbBase: String = env.getOrDefault("POSTGRESQL_ADDON_DB", "ggj23_db")
    val dbUrl = "jdbc:postgresql://$dbHost:$dbPort"
    val dbUser: String = env.getOrDefault("POSTGRESQL_ADDON_USER", "postgres")
    val dbPassword: String = env.getOrDefault("POSTGRESQL_ADDON_PASSWORD", "password")


    println("Config : $dbUrl, $dbBase, $dbUser")
//    setupDatabase(dbUrl, dbBase, dbUser, dbPassword)
    Database.connect("$dbUrl/$dbBase", "org.postgresql.Driver", dbUser, dbPassword)

    transaction {
        SchemaUtils.create(Users)
        SchemaUtils.create(Parcels)
    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, KotlinxSerializationConverter(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }))
    }

    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Delete)
        anyHost()
    }

    install(Compression) {
        gzip()
    }

    install(Sessions) {
        cookie<UserSession>("user_session")
    }

    install(Authentication) {
        session<UserSession>("auth-session") {
            validate {
                transaction {
                    val user = Users.select(Users.id eq it.userId)
                        .singleOrNull()
                    if (user != null) it else null
                }
            }
            challenge {
                call.respondRedirect("/app/login")
            }
        }
    }

    routing {
        static("/") {
            resources("/")
            resource("/", "index.html")
            resource("style.css")
            resource("ggj23000.js")
            resource("/app", "index.html")
            resource("/app/*", "index.html")
            resource("favicon.ico")
            resource("mainView.js")
        }

        authenticate("auth-session") {
            route("/api") {
                get("/users/me") {
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?
                    var user: UserMe? = null
                    transaction {
                        user = Users.select(Users.id eq userSession!!.userId)
                            .map { resultRow ->
                                UserMe(User(resultRow[Users.id].value, resultRow[Users.name]), resultRow[Users.tag])
                            }
                            .singleOrNull()
                    }
                    if (user == null) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(user!!)

                }


                get("/parcels/mine") {
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?
                    val parcel = getParcelFromSession(userSession)

                    call.respond(parcel!!)
                }

                get("/parcels/mine/harvest/wood"){
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?
                    val parcel = getParcelFromSession(userSession)
                    call.respond(parcel?.harvestWood()!!)
                }

                //get("/parcels/mine/harvest/fruits"){
                //    call.respond(parcel.harvestFruits())
                //}
                //get("/parcels/mine/harvest/iron"){
                //    call.respond(parcel.harvestIron())
                ///}
            }
        }
        route("/sign-up") {
            post() {
                val userName = call.receiveParameters()["usrname"]
                if (userName != null && userName.isBlank()) {
                    call.respondRedirect("/app/login")
                    return@post
                }
                var newUser: UserMe? = null
                transaction {
                    val userInsertion = UserDao.new {
                        this.name = userName!!
                        this.tag = UserMe.randomTag()
                    }
                    newUser = UserMe(User(userInsertion.id.value, userInsertion.name), userInsertion.tag)
                    var parcel = Parcel();

                    ParcelDao.new {
                        this.user = userInsertion.id.value
                        this.rsWoodQuantity = parcel.resourceStorage.wood.quantity
                        this.rsWoodCapacity = parcel.resourceStorage.wood.capacity
                        this.rsFruitsQuantity = parcel.resourceStorage.fruits.quantity
                        this.rsFruitsCapacity = parcel.resourceStorage.fruits.capacity
                        this.rsIronQuantity = parcel.resourceStorage.iron.quantity
                        this.rsIronCapacity = parcel.resourceStorage.iron.capacity

                        this.iBioVal = parcel.indicators.bio.value
                        this.iBioEvo = parcel.indicators.bio.evolution

                        this.iAirVal = parcel.indicators.air.value
                        this.iAirEvo = parcel.indicators.air.evolution

                        this.iSoilVal = parcel.indicators.soil.value
                        this.iSoilEvo = parcel.indicators.soil.evolution

                        this.nrIron = parcel.naturalResources.iron
                        this.nrFruits = parcel.naturalResources.fruits
                        this.nrTrees = parcel.naturalResources.trees

                    }
                }
                call.sessions.set("user_session", UserSession(newUser?.publicUser!!.userId))
                call.application.environment.log.info("New user : $newUser")
                call.respondRedirect("/app/home")
            }
        }

        route("/sign-in") {
            post {
                val userTag = call.receiveParameters()["usertag"]
                if (userTag != null && userTag.isBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "bad usertag")
                    return@post
                }
                var user: UserMe? = null
                transaction {
                    user = Users.select(Users.tag eq userTag!!)
                        .map { resultRow ->
                            UserMe(User(resultRow[Users.id].value, resultRow[Users.name]), resultRow[Users.tag])
                        }
                        .singleOrNull()
                }
                if (user != null) {
                    call.sessions.set("user_session", UserSession(user?.publicUser!!.userId))
                    call.application.environment.log.info("User $user signed in")
                    call.respondRedirect("/app/home")
                } else {
                    call.application.environment.log.info("User not found")
                    call.respondRedirect("/app/login")
                }
            }
        }
    }

    Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(
        UpdateWorldJob()::updateWorld,
        0, 5, TimeUnit.SECONDS
    )
}


private fun setupDatabase(url: String, base: String, user: String, password: String) {
    Database.connect("$url/", "org.postgresql.Driver", user, password)
    try {
        transaction {
            connection.autoCommit = true
            SchemaUtils.createDatabase(base)
            connection.autoCommit = false
        }
    } catch (_: Exception) {

    }
}
