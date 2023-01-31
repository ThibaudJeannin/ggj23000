import ggj.User
import react.RBuilder
import react.dom.h2

val Greetings: RBuilder.() -> Unit = {
    h2 {
        //TODO implémenter session
        val user = User("1234", "[VOTRE NOM ICI]")
        +"Bonjour $user"
    }
}
