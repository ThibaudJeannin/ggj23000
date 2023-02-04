import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.browser.document
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
    followRedirects = false
    expectSuccess = false

    HttpResponseValidator {
        validateResponse { response ->
            console.log("ResponseValidator : $response")
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

fun redirect(redirectLocation: String) {
    window.location.replace(redirectLocation)
    window.location.href = redirectLocation
    document.location!!.replace(redirectLocation)
    document.location!!.href = redirectLocation
}

val app = fc<PropsWithChildren> {

    BrowserRouter {
        Routes {
            Route {
                attrs {
                    index = true
                    path = "/"
                    element = MainView.create()
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
