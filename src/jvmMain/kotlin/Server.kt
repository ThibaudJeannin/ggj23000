import ggj.User
import ggj23.UserSession
import ggj23.dao.UserDao
import ggj23.dao.Users
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
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


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    setupDatabase()

    Database.connect("jdbc:postgresql://localhost:5432/test_db", "org.postgresql.Driver", "postgres", "password")
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

    routing {
        static("/") {
            resource("/", "index.html")
            resource("style.css")
            resource("ggj23000.js")
            resource("/app/*", "index.html")
            resource("favicon.ico")
        }

        route("/sign-up") {
            post() {
                val userName = call.receiveParameters()["username"]
                if (userName != null && userName.isBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "bad username")
                    return@post
                }
                val newUser = User(userName = userName!!)
                transaction {
                    UserDao.createFrom(newUser)
                }
                call.sessions.set("user_session", UserSession(newUser))
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
                var user: User? = null
                transaction {
                    user = Users.select(Users.id eq userTag)
                        .map { resultRow ->
                            User(resultRow[Users.id].value, resultRow[Users.name])
                        }
                        .singleOrNull()
                }
                if (user != null) {
                    call.sessions.set("user_session", UserSession(user!!))
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

private fun setupDatabase() {
    Database.connect("jdbc:postgresql://localhost:5432/", "org.postgresql.Driver", "postgres", "password")
    transaction {
        connection.autoCommit = true
        SchemaUtils.dropDatabase("test_db")
        SchemaUtils.createDatabase("test_db")
        connection.autoCommit = false
    }
}