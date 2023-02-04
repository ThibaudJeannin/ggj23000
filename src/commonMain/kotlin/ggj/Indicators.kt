package ggj

import kotlinx.serialization.Serializable

@Serializable
class Indicators() {
    val bio = Indicator()
    val air = Indicator()
    val soil = Indicator()

    fun variate() {
        bio.variate()
        air.variate()
        soil.variate()
    }

}