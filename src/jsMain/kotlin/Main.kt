import dom.Element
import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container: Element = document.getElementById("root")!! as Element
    createRoot(container).render(app.create())
}