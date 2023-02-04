import browser.document
import kotlinx.html.id
import react.Props
import react.dom.attrs
import react.dom.div
import react.dom.script
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

            }
        }

        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                id = "perk_2"
            }
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                id = "perk_3"
            }
        }
        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                id = "perk_4"
            }
        }
        styledDiv {
            css {
                classes.add("user")
            }
            attrs {
                id = "user"
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
    }
    styledDiv {
        css {
            classes.add("subsidebar")
        }
        attrs {
            id = "subsidebar_2"
        }
    }
    styledDiv {
        css {
            classes.add("subsidebar")
        }
        attrs {
            id = "subsidebar_3"
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
                classes.add("perks")
            }
            attrs {
                id = "mine_1"

            }
        }

        styledDiv {
            css {
                classes.add("perks")
            }
            attrs {
                id = "warehouse_1"
            }
        }
    }

    script {
        val js = document.createElement("script")
        js.setAttribute("src", "/mainView.js")
        document.body.appendChild(js)
    }
}
