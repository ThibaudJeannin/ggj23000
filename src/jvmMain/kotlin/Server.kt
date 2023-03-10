import ggj.Parcel
import ggj.UserSession
import ggj.dao.ParcelDao
import ggj.dao.Parcels
import ggj.dao.UserDao
import ggj.dao.Users
import ggj.task.UpdateWorldJob
import ggj.users.User
import ggj.users.UserMe
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
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(args: Array<String>): Unit = EngineMain.main(args)

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
            encodeDefaults = true
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
                call.respond(HttpStatusCode.Forbidden)
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
                        call.respond(HttpStatusCode.Forbidden)
                        return@get
                    }
                    call.respond(user!!)
                }


                get("/parcels/mine") {
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?

                    var res:Parcel? = null

                    transaction {

                        var parcels = ParcelDao.find {
                            Parcels.user eq userSession!!.userId
                        }
                        res = parcels.elementAt(0).toParcel()

                    }

                    call.respond(res!!)
                }

                get("/parcels/mine/harvest/wood") {
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?
                    var res: Parcel? = null

                    transaction {

                        var parcel = ParcelDao.find {
                            Parcels.user eq userSession!!.userId
                        }.singleOrNull()

                        var p = parcel?.toParcel()

                        res = p?.harvestWood()

                        parcel?.applyParcel(p)

                    }

                    call.respond(res!!)
                }

                get("/parcels/mine/harvest/fruits"){
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?
                    var res: Parcel? = null

                    transaction {

                        var parcel = ParcelDao.find {
                            Parcels.user eq userSession!!.userId
                        }.singleOrNull()

                        var p = parcel?.toParcel()

                        res = p?.harvestFruits()
                        parcel?.applyParcel(p)

                    }
                    call.respond(res!!)
                }
                get("/parcels/{resource}/upgrade/{id}") {
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?

                    val upgradeId = call.parameters["id"]
                    val resource = call.parameters["resource"]

                    var res: Parcel? = null

                    transaction {

                        var parcel = ParcelDao.find {
                            Parcels.user eq userSession!!.userId
                        }.singleOrNull()
                        var p = parcel?.toParcel()

                        if (resource == "wood") {
                            res = p?.buyWoodUpgrade(upgradeId)
                        }
                        if (resource == "iron") {
                            res = p?.buyIronUpgrade(upgradeId)
                        }
                        if (resource == "fruits") {
                            res = p?.buyFruitsUpgrade(upgradeId)
                        }

                        parcel?.applyParcel(p)

                    }

                    call.respond(res!!)
                }

                get("/parcels/iron/upgrade") {

                }
                get("/parcels/fruits/upgrade") {

                }
                get("/parcels/mine/harvest/iron"){
                    val userSession: UserSession? = call.sessions.get("user_session") as UserSession?

                    var res: Parcel? = null

                    transaction {

                        var parcel = ParcelDao.find {
                            Parcels.user eq userSession!!.userId
                        }.singleOrNull()

                        var p = parcel?.toParcel()

                        res = p?.harvestIron()

                        parcel?.applyParcel(p)

                    }

                    call.respond(res!!)
                }
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

                    // create parcel user linked into the databse (still empty object)
                    var parceldb = ParcelDao.new {
                        this.user = userInsertion.id.value
                    }

                    // init base parcel datas generated by Parcel object
                    parceldb.applyParcel(parcel)
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
        route("/sign-out") {
            get {
                call.sessions.set("user_session", UserSession(-1))
                call.respondRedirect("/app/login")
            }
        }
    }

    Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(
        UpdateWorldJob()::updateWorld,
        5, 5, TimeUnit.SECONDS
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
