import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.hidden
import react.Props
import react.dom.*
import react.fc
import styled.css
import styled.styledDiv
import styled.styledHr
import styled.styledInput

val LoginForm = fc<Props> {

    styledDiv {
        css {
            classes.add("login-container")
        }

        styledDiv {
            css {
                classes.add("login-form")
            }

            styledDiv {
                css {
                    classes.add("login-form-title")
                }

                + "LOGIN"
            }

            form {
                attrs {
                    action = "/sign-in"
                    method = FormMethod.post
                    autoComplete = false
                }

                styledInput {
                    attrs {
                        type = InputType.text
                        placeholder = "####"
                        name = "usertag"
                        required = true
                        maxLength="4"
                    }
                    css {
                        classes.add("login-form-input")
                        classes.add("login-form-input-id")
                    }
                }

                styledInput {
                    attrs {
                        type = InputType.submit
                        hidden = true
                    }
                }
            }

            styledHr {
                css {
                    classes.add("login-form-separator")
                }
            }

            styledDiv {
                css {
                    classes.add("login-form-title")
                }

                + "SIGNUP"
            }

            form {
                attrs {
                    action = "/sign-up"
                    method = FormMethod.post
                    autoComplete = false
                }
                styledInput {
                    attrs {
                        type = InputType.text
                        placeholder = "Your name here"
                        name = "usrname"
                        required = true
                    }
                    css {
                        classes.add("login-form-input")
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
    }
}
