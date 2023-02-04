import ggj.User
import ggj.UserMe
import ggj.UserSession
import ggj.dao.UserDao
import ggj.dao.Users
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

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val env = System.getenv()
    val host: String = env.getOrDefault("POSTGRESQL_ADDON_HOST", "localhost")
    val port: String = env.getOrDefault("POSTGRESQL_ADDON_PORT", "5432")
    val base: String = env.getOrDefault("POSTGRESQL_ADDON_DB", "test_db")
    val url = "jdbc:postgresql://$host:$port"
    val user: String = env.getOrDefault("POSTGRESQL_ADDON_USER", "postgres")
    val password: String = env.getOrDefault("POSTGRESQL_ADDON_PASSWORD", "password")

    print("$url, $port, $base, $user, $password")

    setupDatabase(url, base, user, password)

    Database.connect("$url/$base", "org.postgresql.Driver", user, password)
    transaction {
        SchemaUtils.create(Users)
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
            resource("/", "index.html")
            resource("style.css")
            resource("ggj23000.js")
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
}

private fun setupDatabase(url: String, base: String, user: String, password: String) {
    Database.connect(url, "org.postgresql.Driver", user, password)
    try {
        transaction {
            connection.autoCommit = true
            SchemaUtils.createDatabase(base)
            connection.autoCommit = false
        }
    } catch (_: Exception) {

    }
}
