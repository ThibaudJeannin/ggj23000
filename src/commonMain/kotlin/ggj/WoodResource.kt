package ggj


class WoodResource(quantity: Float = 0.0f, capacity: Float): StoredResource(quantity, capacity) {

    override fun produce(indicators: Indicators, naturalResources: NaturalResources) {
        this.quantity = minOf(this.capacity, this.quantity + (naturalResources.trees * indicators.bio))
    }

}