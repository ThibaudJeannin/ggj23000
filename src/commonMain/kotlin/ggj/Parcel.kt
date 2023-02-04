package ggj

import kotlinx.serialization.Serializable
import kotlin.math.abs
import kotlin.random.Random

@Serializable
class Parcel() {

    val resourceStorage = ResourceStorage()
    val indicators = Indicators()
    val naturalResources = NaturalResources()
    val items = mutableListOf<Item>()

    private var harvestWoodValue = 10.0f
    private var harvestFruitValue = 2.0f

    private var harvestWoodRate = 1
    private var harvestFruitRate = 1
    private var harvestIronRate = 1

    private var seedLootRate = 5; // in %

    fun produceResources() {
        resourceStorage.addProduction(Production(constants.wood.PRODUCTION, constants.fruits.PRODUCTION, constants.iron.PRODUCTION))
    }

    fun harvestWood(): Parcel {
        if (naturalResources.trees - harvestWoodRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient wood")
        }

        naturalResources.trees--
        resourceStorage.wood.addToStorage(harvestWoodValue)

        indicators.bio.value -= constants.wood.BIO_MODIFYER
        indicators.bio.evolution -= constants.wood.BIO_EVO_MODIFYER

        indicators.air.evolution -= constants.wood.AIR_EVO_MODIFYER
        indicators.air.value -= constants.wood.AIR_MODIFYER

        indicators.soil.evolution -= constants.wood.SOIL_EVO_MODIFYER
        indicators.soil.value -= constants.wood.SOIL_MODIFYER

        if (abs(Random.nextInt()) % 100 < seedLootRate) {
            this.items.add(Seed())
        }

        return this
    }

    fun harvestFruits(): Parcel {

        if (naturalResources.fruits - harvestFruitRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient fruits")
        }

        resourceStorage.fruits.addToStorage(harvestFruitValue)

        indicators.bio.value -= constants.fruits.BIO_MODIFYER
        indicators.bio.evolution -= constants.fruits.BIO_EVO_MODIFYER

        indicators.air.evolution -= constants.fruits.AIR_EVO_MODIFYER
        indicators.air.value -= constants.fruits.AIR_MODIFYER

        indicators.soil.evolution -= constants.fruits.SOIL_EVO_MODIFYER
        indicators.soil.value -= constants.fruits.SOIL_MODIFYER

        if (abs(Random.nextInt()) % 100 < (seedLootRate * 2)) {
            this.items.add(Seed())
        }

        return this
    }

    fun harvestIron(): Parcel {

        if (naturalResources.iron - harvestIronRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient iron")
        }


        indicators.bio.value -= constants.iron.BIO_MODIFYER
        indicators.bio.evolution -= constants.iron.BIO_EVO_MODIFYER

        indicators.air.evolution -= constants.iron.AIR_EVO_MODIFYER
        indicators.air.value -= constants.iron.AIR_MODIFYER

        indicators.soil.evolution -= constants.iron.SOIL_EVO_MODIFYER
        indicators.soil.value -= constants.iron.SOIL_MODIFYER

        if (abs(Random.nextInt()) % 100 < (constants.iron.HARVEST_PROBA)) {

            val rng = (constants.iron.MIN_HARVEST_AMOUNT..constants.iron.MAX_HARVEST_AMOUNT).random().toFloat()

            resourceStorage.iron.addToStorage(rng)
        }

        return this
    }
}
