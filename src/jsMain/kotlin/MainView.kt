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

                    + "100"
                }
            }

            styledDiv {
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

                    + "100"
                }
            }

            styledDiv {
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

                    + "100"
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

                        + "BIO"
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

                        + "AIR"
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

                        + "SOL"
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