package ggj.indicators

import kotlinx.serialization.Serializable

@Serializable
class Indicator(var value: Float = 1.0f, var evolution: Float = 0.05f) {
    override fun toString(): String{
        return "{value: ${this.value}, evolution: ${this.evolution}}"
    }
    fun variate() {
        this.value = maxOf(0.0f, minOf(1.0f, this.value + this.evolution))
    }
}