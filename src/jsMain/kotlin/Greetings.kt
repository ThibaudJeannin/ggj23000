import ggj.User
import ggj.UserMe
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import react.Props
import react.dom.a
import react.dom.attrs
import react.dom.h2
import react.fc
import react.useEffectOnce
import react.useState

val Greeting = fc<Props> {

    var user: UserMe by useState(UserMe(User(0, "[your name here]"), "1234"))

    useEffectOnce {
        scope.launch {
            user = getCurrentUser()
        }
    }

    console.log("Greeting")
    h2 {
        +"Bonjour $user"
    }

    a {
        attrs {
            href = "/app/disconnect"
        }
        +"Logout"
    }


}

suspend fun getCurrentUser(): UserMe {
    console.log("retrieve current user")
    return httpClient.get("${baseUrl}/api/users/me").body()
}

