package ggj

class StoredResource(var quantity: Float = 0.0f, var capacity: Float = 100.0f) {
    fun addToStorage(amount: Float) {
        this.quantity = minOf(this.capacity, this.quantity + amount);
    }
}