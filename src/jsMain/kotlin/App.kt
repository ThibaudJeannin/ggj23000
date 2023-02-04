import io.ktor.client.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.serialization.json.Json
import react.Props
import react.PropsWithChildren
import react.create
import react.fc
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

val scope = MainScope()

val baseUrl = "${window.location.protocol}//${window.location.host}"

val httpClient = HttpClient {
    install(ResponseObserver) {
        onResponse { response ->
            if (response.status.value >= 300) {
                println("HTTP status: ${response.status.value}")
            }
        }
    }
    install(ContentNegotiation) {
        register(ContentType.Application.Json, KotlinxSerializationConverter(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }))
    }

    install(HttpCache)
}

val app = fc<PropsWithChildren> {

    BrowserRouter {
        Routes {
            Route {
                attrs {
                    index = true
                    path = "/"
                    element = LoginForm.create()
                }
            }
            Route {
                attrs {
                    path = "/app/login"
                    element = LoginForm.create()
                }
            }
            Route {
                attrs {
                    path = "/app/home"
                    element = MainView.create()
                }
            }
            Route {
                attrs {
                    path = "/app/disconnect"
                    element = fc<Props> {
                        window.location.replace("/app/login")
                        TODO("Implémenter la déconnexion")
                    }.create()
                }
            }
        }
    }
}
