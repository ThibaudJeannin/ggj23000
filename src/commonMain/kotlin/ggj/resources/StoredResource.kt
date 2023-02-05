package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class StoredResource(var quantity: Float = 0.0f, var capacity: Float = 100.0f) {
    fun addToStorage(amount: Float) {
        this.quantity = minOf(this.capacity, this.quantity + amount);
    }

    override fun toString(): String {
        return "(quantity: ${this.quantity}, capacity: ${this.capacity})";
    }
}