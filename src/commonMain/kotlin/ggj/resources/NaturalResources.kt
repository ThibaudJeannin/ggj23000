package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class NaturalResources(var iron: Float = 500f, var fruits: Float = 20000f, var trees: Float = 10000f, var animals: Float = 1000f) {
}