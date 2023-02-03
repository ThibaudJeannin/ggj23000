package ggj

abstract class StoredResource(var quantity: Float = 0.0f, var capacity: Float) {
    abstract fun produce(indicators: Indicators, naturalResources: NaturalResources);
}