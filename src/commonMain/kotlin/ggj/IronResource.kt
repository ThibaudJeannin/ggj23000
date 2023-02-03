package ggj


class IronResource(name: String, quantity: Float = 0.0f, capacity: Float): StoredResource(name, quantity, capacity) {

    override fun produce(bio: Float) {
        this.quantity = minOf(this.capacity, this.quantity + bio);
    }

}