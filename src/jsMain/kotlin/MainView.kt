import browser.document
import kotlinx.html.id
import react.Props
import react.dom.*
import react.fc
import styled.css
import styled.styledDiv

val MainView = fc<Props> {

    div {
        attrs {
            id = "container"
        }

        div {
            attrs {
                id = "resources"
            }

            styledDiv {
                attrs {
                    id = "wood"
                }
                css {
                    classes.add("resource")
                }

                styledDiv {
                    css {
                        classes.add("resource-img")
                    }
                }

                styledDiv {
                    css {
                        classes.add("resource-text")
                    }
                }
            }

            styledDiv {
                attrs {
                    id = "fruits"
                }
                css {
                    classes.add("resource")
                }

                styledDiv {
                    css {
                        classes.add("resource-img")
                    }
                }

                styledDiv {
                    css {
                        classes.add("resource-text")
                    }
                }
            }

            styledDiv {
                attrs {
                    id = "iron"
                }
                css {
                    classes.add("resource")
                }

                styledDiv {
                    css {
                        classes.add("resource-img")
                    }
                }

                styledDiv {
                    css {
                        classes.add("resource-text")
                    }
                }
            }
        }

        div {
            attrs {
                id = "bars"
            }

            styledDiv {
                css {
                    classes.add("bar")
                }

                styledDiv {
                    css {
                        classes.add("bar-container")
                    }

                    styledDiv {
                        css {
                            classes.add("bar-text")
                        }

                        +"BIO"
                    }

                    styledDiv {
                        css {
                            classes.add("bar-bar")
                        }
                    }
                }
            }

            styledDiv {
                css {
                    classes.add("bar")
                }

                styledDiv {
                    css {
                        classes.add("bar-container")
                    }

                    styledDiv {
                        css {
                            classes.add("bar-text")
                        }

                        +"AIR"
                    }

                    styledDiv {
                        css {
                            classes.add("bar-bar")
                        }
                    }
                }
            }

            styledDiv {
                css {
                    classes.add("bar")
                }

                styledDiv {
                    css {
                        classes.add("bar-container")
                    }

                    styledDiv {
                        css {
                            classes.add("bar-text")
                        }

                        +"SOL"
                    }

                    styledDiv {
                        css {
                            classes.add("bar-bar")
                        }
                    }
                }
            }
        }

        div {
            attrs {
                id = "gameview"
            }

            div {
                attrs {
                    id = "actions-container"
                }

                div {
                    attrs {
                        id = "actions"
                    }

                    styledDiv {
                        css {
                            classes.add("action")
                        }
                        attrs {
                            id = "axe"
                        }
                    }

                    styledDiv {
                        css {
                            classes.add("action")
                        }
                        attrs {
                            id = "hoe"
                        }
                    }

                    styledDiv {
                        css {
                            classes.add("action")
                        }
                        attrs {
                            id = "pickaxe"
                        }
                    }
                }
            }
        }

        div {
            attrs {
                id = "items"
            }

            styledDiv {
                css {
                    classes.add("item")
                }
                attrs {
                    id = "conso"
                }
                styledDiv {
                    css {
                        classes.add("item-img")
                    }
                    attrs {
                        id = "consoImg"
                    }
                }
                styledDiv {
                    css {
                        classes.add("item-text")
                    }
                    +"CONSOMMABLES"
                }
            }
            styledDiv {
                css {
                    classes.add("item")
                }
                attrs {
                    id = "objet"
                }
                styledDiv {
                    css {
                        classes.add("item-img")
                    }
                    attrs {
                        id = "objetImg"
                    }
                }
                styledDiv {
                    css {
                        classes.add("item-text")
                    }
                    +"OBJETS"
                }
            }
            styledDiv {
                css {
                    classes.add("item")
                }
                attrs {
                    id = "shop"
                }
                styledDiv {
                    css {
                        classes.add("item-img")
                    }
                    attrs {
                        id = "shopImg"
                    }
                }
                styledDiv {
                    css {
                        classes.add("item-text")
                    }
                    +"MAGASIN"
                }
            }
        }
    }


    styledDiv {
        css {
            classes.add("strip")
        }
        attrs {
            id = "strip_1"
            onClick = {
                console.log(document.getElementById("sidebar"))
                document.getElementById("sidebar")?.classList?.toggle("opened")
                document.getElementById("strip_1")?.classList?.toggle("opened")
            }
        }
    }

    div {
        attrs {
            id = "sidebar"
        }

        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                id = "perk_1"
                onClick = {
                    document.getElementById("subsidebar_1")?.classList?.toggle("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                }
            }
            img {
                attrs {
                    id = "perks_1"
                    src = "../../assets/perks/basics.png"
                }
            }
            + "Commun"
        }

        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                onClick = {
                    document.getElementById("subsidebar_2")?.classList?.toggle("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                }
            }
            img {
                attrs {
                    id = "perks_2"
                    src = "../../assets/perks/wood.png"
                }
            }
            + "Bois"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                onClick = {
                    document.getElementById("subsidebar_3")?.classList?.toggle("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                }
            }
            img {
                attrs {
                    id = "perks_3"
                    src = "../../assets/perks/fruits.png"
                }
            }
            + "Fruits"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                onClick = {
                    document.getElementById("subsidebar_4")?.classList?.toggle("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                }
            }
            img {
                attrs {
                    id = "perks_4"
                    src = "../../assets/perks/fer.png"
                }
            }
            + "Fer"
        }
        styledDiv {
            css {
                classes.add("user")
            }
            attrs {
                id = "user"
            }
            styledDiv {
                attrs {
                    id ="user-greetings"
                }
            }
            a {
                attrs {
                    href = "/sign-out"
                }
                +"Se déconnecter"
            }
        }
    }

    styledDiv {
        css {
            classes.add("subsidebar")
        }
        attrs {
            id = "subsidebar_1"
        }
        styledDiv {
            css {
                classes.add("closeSub")
            }
            attrs {
                id = "close_1"
                onClick = {
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                }
            }
            + "X"
        }
    }
    styledDiv {
        css {
            classes.add("subsidebar")
        }
        attrs {
            id = "subsidebar_2"
        }
        styledDiv {
            css {
                classes.add("closeSub")
            }
            attrs {
                id = "close_2"
                onClick = {
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                }
            }
            + "X"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "cabane_1"
                    src = "../../assets/perks/mill_storage.png"
                }
            }
            +"Niveau 0"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "timber_1"
                    src = "../../assets/perks/mill_cutter.png"
                }
            }
            +"Niveau 0"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "forest_1"
                    src = "../../assets/perks/mill_crane.png"
                }
            }
            +"Niveau 0"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "fred_1"
                    src = "../../assets/perks/mill_warehouse.png"
                }
            }
            +"Niveau 0"
        }
    }
    styledDiv {
        css {
            classes.add("subsidebar")
        }
        attrs {
            id = "subsidebar_3"
        }
        styledDiv {
            css {
                classes.add("closeSub")
            }
            attrs {
                id = "close_3"
                onClick = {
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                }
            }
            + "X"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "hangar_1"
                    src = "../../assets/perks/mill_stoneWarehouse.png"
                }
            }
            +"Niveau 1"
        }
    }
    styledDiv {
        css {
            classes.add("subsidebar")
        }
        attrs {
            id = "subsidebar_4"
        }
        styledDiv {
            css {
                classes.add("closeSub")
            }
            attrs {
                id = "close_4"
                onClick = {
                    document.getElementById("subsidebar_4")?.classList?.remove("opened")
                    document.getElementById("subsidebar_1")?.classList?.remove("opened")
                    document.getElementById("subsidebar_2")?.classList?.remove("opened")
                    document.getElementById("subsidebar_3")?.classList?.remove("opened")
                }
            }
            + "X"
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "mine_1"
                    src = "../../assets/perks/medieval_mine.png"
                }
            }
            +"Niveau 0"
        }

        styledDiv {
            css {
                classes.add("perks")
            }
            img {
                attrs {
                    id = "warehouse_1"
                    src = "../../assets/perks/medieval_archery.png"
                }
            }
            +"Niveau 0"
        }
    }

    script {
        val js = document.createElement("script")
        js.setAttribute("src", "/mainView.js")
        document.body.appendChild(js)
    }
}

