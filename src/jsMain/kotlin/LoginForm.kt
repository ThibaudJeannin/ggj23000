import io.ktor.client.request.*
import kotlinx.coroutines.launch
import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.hidden
import react.Props
import react.dom.attrs
import react.dom.form
import react.dom.h1
import react.dom.h3
import react.fc
import react.useEffectOnce
import styled.css
import styled.styledDiv
import styled.styledHr
import styled.styledInput

val LoginForm = fc<Props> {

    useEffectOnce {
        scope.launch {
            console.log("retrieve current user")
            val getResponse = httpClient.get("${baseUrl}/api/users/me")
            if (getResponse.status.value != 403) {
                redirect("/app/home")
            }
        }
    }

    styledDiv {
        css {
            classes.add("login-container")
        }

        h1 {
            +"Forêt 3000 \uD83C\uDF33\uD83C\uDF32"
        }

        h3 {
            +"Jeu de gestion de forêt. Obtenez la racine magique pour sauver la planète. Mais attention, chaque action a des conséquences sur votre foret !"
        }

        styledDiv {
            css {
                classes.add("login-form")
            }

            styledDiv {
                css {
                    classes.add("login-form-title")
                }

                +"Se connecter"
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
                        maxLength = "4"
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

                +"Inscription"
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
                        placeholder = "Votre nom"
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
