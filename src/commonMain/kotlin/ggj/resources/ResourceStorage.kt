package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class ResourceStorage {
    val wood = StoredResource(0.0f, 1000.0f)
    val fruits = StoredResource(0.0f, 1000.0f)
    val iron = StoredResource(0.0f, 1000.0f)
    override fun toString(): String {
        return "wood: ${this.wood}, fruits: ${this.fruits}, iron: ${this.iron}";
    }
    fun addProduction(production: Production) {
        wood.addToStorage(production.wood)
        fruits.addToStorage(production.fruits)
        iron.addToStorage(production.iron)
    }

}
