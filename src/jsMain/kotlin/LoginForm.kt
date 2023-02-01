import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.hidden
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.form
import react.dom.input
import react.fc
import styled.styledInput

val LoginForm = fc<Props> {

    console.log("Login form")

    form {
        attrs {
            action = "/sign-up"
            method = FormMethod.post
            autoComplete = false
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
            autoComplete = false
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
