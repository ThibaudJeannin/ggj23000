package ggj


class FruitsResource : StoredResource(5.0f, 10.0f) {

    override fun produce(indicators: Indicators, naturalResources: NaturalResources) {
        this.quantity = minOf(this.capacity, this.quantity + (naturalResources.trees * indicators.bio));
    }

}