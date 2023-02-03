package ggj

class WoodResource(var quantity: Float = 0.0f) {

    fun produce(bio: Float) {
        this.quantity += bio
    }
}