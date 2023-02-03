package ggj


class IronResource : StoredResource(50.0f, 75.0f) {

    override fun produce(indicators: Indicators, naturalResources: NaturalResources) {
        this.quantity = minOf(this.capacity, this.quantity + 5);
    }
}