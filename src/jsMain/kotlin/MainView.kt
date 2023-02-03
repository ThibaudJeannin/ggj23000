import browser.document
import kotlinx.html.id
import react.Props
import react.dom.*
import react.fc

val MainView = fc<Props> {

    div {
        attrs {
            id = "container"
        }

        div {
            attrs {
                id = "resources"
            }
        }

        div {
            attrs {
                id = "bars"
            }
        }

        div {
            attrs {
                id = "gameview"
            }
        }

        div {
            attrs {
                id = "items"
            }
        }
    }

    div {
        attrs {
            id = "sidebar"
        }

    }

    script {
        var js = document.createElement("script")
        js.setAttribute("src", "mainView.js")
        document.body.appendChild(js)
    }
}