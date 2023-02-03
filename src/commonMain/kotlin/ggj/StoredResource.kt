package ggj

abstract class StoredResource(val name: String, var quantity: Float = 0.0f, var capacity: Float) {
    abstract fun produce(bio: Float);
}