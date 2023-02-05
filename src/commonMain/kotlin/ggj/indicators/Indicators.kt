package ggj.indicators

import kotlinx.serialization.Serializable

@Serializable
class Indicators() {
    val bio = Indicator()
    val air = Indicator()
    val soil = Indicator()
    override fun toString(): String {
        return "bio : ${bio.toString()}, air : ${air.toString()}, soil: ${soil.toString()}"
    }
    fun variate() {
        bio.variate()
        air.variate()
        soil.variate()
    }

}