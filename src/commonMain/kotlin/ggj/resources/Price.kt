package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class Price(val wood: Int, val fruits: Int, val iron: Int, val time: Float) {
}