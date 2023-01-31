import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.hidden
import react.RBuilder
import react.dom.attrs
import react.dom.form
import react.dom.input
import styled.styledInput

val LoginForm: RBuilder.() -> Unit = {

    form {
        attrs {
            action = "/sign-up"
            method = FormMethod.post
        }
        input {
            attrs {
                type = InputType.text
                placeholder = "Username"
                name = "username"
                required = true
            }

        }
        styledInput {
            attrs {
                type = InputType.submit
                hidden = true
            }
        }
    }
    form {
        attrs {
            action = "/sign-in"
            method = FormMethod.post
        }
        input {
            attrs {
                type = InputType.text
                placeholder = "#1234"
                name = "usertag"
                required = true
            }
        }
        styledInput {
            attrs {
                type = InputType.submit
                hidden = true
            }
        }
    }
}
