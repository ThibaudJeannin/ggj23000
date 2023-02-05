package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class Price(val woodCost: Int, val fruitCost: Int, val ironCost: Int, val time: Float) {
}