package ggj

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