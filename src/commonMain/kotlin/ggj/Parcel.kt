package ggj

import kotlin.math.abs
import kotlin.random.Random

class Parcel() {

    val resourceStorage = ResourceStorage()
    val indicators = Indicators()
    val naturalResources = NaturalResources()
    val items = mutableListOf<Item>()

    private var harvestWoodValue = 10.0f
    private var harvestFruitValue = 2.0f
    private var seedLootRate = 5;

    fun produceResources() {
        resourceStorage.addProduction(Production(5.0f, 5.0f, 5.0f))
    }

    fun harvestWood() {
        if (naturalResources.trees - 1 < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient wood")
        }

        naturalResources.trees--
        resourceStorage.wood.addToStorage(harvestWoodValue)
        indicators.bio.value -= 0.02f
        indicators.bio.evolution -= 0.001f
        indicators.air.evolution -= 0.0001f

        if (abs(Random.nextInt()) % 100 < seedLootRate) {
            this.items.add(Seed())
        }
    }

    fun harvestFruits() {

        resourceStorage.fruits.addToStorage(harvestFruitValue)
        indicators.bio.evolution -= 0.001f

        if (abs(Random.nextInt()) % 100 < (seedLootRate * 2)) {
            this.items.add(Seed())
        }
    }
}