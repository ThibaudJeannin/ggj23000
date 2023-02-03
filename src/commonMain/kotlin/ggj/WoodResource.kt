package ggj


class WoodResource(quantity: Float = 0.0f, capacity: Float): StoredResource(quantity, capacity) {

    override fun produce(bio: Float) {
        this.quantity = minOf(this.capacity, this.quantity + bio);
    }

}