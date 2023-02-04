package ggj

import kotlinx.serialization.Serializable

@Serializable
class StoredResource(var quantity: Float = 0.0f, var capacity: Float = 100.0f) {
    fun addToStorage(amount: Float) {
        this.quantity = minOf(this.capacity, this.quantity + amount);
    }
}