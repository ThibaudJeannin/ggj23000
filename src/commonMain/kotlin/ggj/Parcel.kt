package ggj

import kotlin.random.Random

class Parcel() {

    val resourceStorage = ResourceStorage()
    val indicators = Indicators()
    val naturalResources = NaturalResources()
    val items = mutableListOf<Item>()

    fun produceResources() {
        resourceStorage.addProduction(Production(5.0f, 5.0f, 5.0f))
    }

    fun harvestWood() {
        if (naturalResources.trees - 1 < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient wood")
        }
        naturalResources.trees--
        resourceStorage.wood.addToStorage(10.0f)
        indicators.bio.value -= 0.02f
        indicators.bio.evolution -= 0.001f
        indicators.air.evolution -= 0.0001f
        if (Random.nextInt() % 100 < 15) {
            this.items.add(Seed())
        }
    }
}