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

    class Indicator(var value: Float = 1.0f, var evolution: Float = 0.05f) {
        fun variate() {
            this.value = maxOf(0.0f, minOf(1.0f, this.value + this.evolution))
        }
    }
}