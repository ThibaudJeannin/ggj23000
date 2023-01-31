import kotlinx.coroutines.MainScope
import react.Props
import react.VFC
import react.createElement
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

private val scope = MainScope()

val app = VFC {
    BrowserRouter {
        Routes {
            Route {
                index = true
                path = "/"
                element = createElement<Props>(LoginForm)
            }
            Route {
                path = "/app/login"
                element = createElement<Props>(LoginForm)
            }
            Route {
                path = "/app/home"
                element = createElement<Props>(Greetings)
            }
        }
    }
}
