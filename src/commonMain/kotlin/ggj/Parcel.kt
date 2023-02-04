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
    private var harvestWoodRate = 1
    private var harvestFruitRate = 1
    // %
    private var seedLootRate = 5;

    fun produceResources() {
        resourceStorage.addProduction(Production(WOOD_PRODUCTION, FRUITS_PRODUCTION, IRON_PRODUCTION))
    }

    fun harvestWood() {
        if (naturalResources.trees - harvestWoodRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient wood")
        }

        naturalResources.trees--
        resourceStorage.wood.addToStorage(harvestWoodValue)

        indicators.bio.value -= WOOD_BIO_MODIFYER
        indicators.bio.evolution -= WOOD_BIO_EVO_MODIFYER
        indicators.air.evolution -= WOOD_AIR_EVO_MODIFYER

        if (abs(Random.nextInt()) % 100 < seedLootRate) {
            this.items.add(Seed())
        }
    }

    fun harvestFruits() {

        if (naturalResources.fruits - harvestFruitRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient fruits")
        }

        resourceStorage.fruits.addToStorage(harvestFruitValue)

        indicators.bio.evolution -= FRUIT_BIO_EVO_MODIFYER

        if (abs(Random.nextInt()) % 100 < (seedLootRate * 2)) {
            this.items.add(Seed())
        }
    }
}