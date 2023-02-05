package ggj

import ggj.indicators.Indicators
import ggj.items.ItemsStorage
import ggj.items.Seed
import ggj.resources.*
import kotlinx.serialization.Serializable
import kotlin.math.abs
import kotlin.random.Random

@Serializable
class Parcel() {

    val resourceStorage = ResourceStorage()
    val indicators = Indicators()
    val naturalResources = NaturalResources()
    val items = ItemsStorage()

    var woodCurrentUpgrade = WoodUpgrade()
    var fruitsCurrentUpgrade = FruitsUpgrade()
    var ironCurrentUpgrade = IronUpgrade()
    //val fruitsCurrentUpgrade = WoodUpgrade()
    //val ironCurrentUpgrade = WoodUpgrade()

    private var harvestWoodValue = 10.0f
    private var harvestFruitValue = 2.0f
    private var harvestIronValue = 1.0f

    private var harvestWoodRate = 1
    private var harvestFruitRate = 1
    private var harvestIronRate = 1

    private var seedLootRate = 15; // in %

    override fun toString():String {
        return "Storage : ${this.resourceStorage} / Indicators : ${this.indicators} / Natural : ${this.naturalResources}"
    }
    private fun check(price: Price?): Boolean {
        if (price != null && this.resourceStorage.iron.quantity > price.iron && this.resourceStorage.wood.quantity > price.wood && this.resourceStorage.fruits.quantity > price.fruits) {

            this.resourceStorage.iron.quantity -= price.iron
            this.resourceStorage.fruits.quantity -= price.fruits
            this.resourceStorage.wood.quantity -= price.wood

            return true
        }
        return false
    }
    fun buyWoodUpgrade(index: String?): Parcel {
        val price = woodUpgrades[index]?.first
        if(check(price)) {
            this.woodCurrentUpgrade = woodUpgrades[index]?.second!!
        }
        return this
    }

    fun buyIronUpgrade(index: String?): Parcel {
        val price = ironUpgrades[index]?.first
        if(check(price)) {
            this.ironCurrentUpgrade = ironUpgrades[index]?.second!!
        }
        return this
    }

    fun buyFruitsUpgrade(index: String?): Parcel {
        val price = fruitsUpgrades[index]?.first
        if(check(price)) {
            this.fruitsCurrentUpgrade = fruitsUpgrades[index]?.second!!
        }
        return this
    }

    fun produceResources() {
        resourceStorage.addProduction(
            Production(
                constants.wood.PRODUCTION,
                constants.fruits.PRODUCTION,
                constants.iron.PRODUCTION
            )
        )
    }

    fun harvestWood(): Parcel {
        if (naturalResources.trees - harvestWoodRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient wood")
        }

        naturalResources.trees -= harvestWoodRate
        resourceStorage.wood.addToStorage(harvestWoodValue)

        indicators.bio.value -= constants.wood.BIO_MODIFYER
        indicators.bio.evolution -= constants.wood.BIO_EVO_MODIFYER

        indicators.air.evolution -= constants.wood.AIR_EVO_MODIFYER
        indicators.air.value -= constants.wood.AIR_MODIFYER

        indicators.soil.evolution -= constants.wood.SOIL_EVO_MODIFYER
        indicators.soil.value -= constants.wood.SOIL_MODIFYER

        if (abs(Random.nextInt()) % 100 < seedLootRate) {
            this.items.seeds.add(Seed())
        }

        return this
    }

    fun harvestFruits(): Parcel {

        if (naturalResources.fruits - harvestFruitRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient fruits")
        }

        naturalResources.fruits -= harvestFruitRate;
        resourceStorage.fruits.addToStorage(harvestFruitValue)

        indicators.bio.value -= constants.fruits.BIO_MODIFYER
        indicators.bio.evolution -= constants.fruits.BIO_EVO_MODIFYER

        indicators.air.evolution -= constants.fruits.AIR_EVO_MODIFYER
        indicators.air.value -= constants.fruits.AIR_MODIFYER

        indicators.soil.evolution -= constants.fruits.SOIL_EVO_MODIFYER
        indicators.soil.value -= constants.fruits.SOIL_MODIFYER

        if (abs(Random.nextInt()) % 100 < (seedLootRate * 2)) {
            this.items.seeds.add(Seed())
        }

        return this
    }

    fun harvestIron(): Parcel {

        if (naturalResources.iron - harvestIronRate < 0) {
            throw InsufficientNaturalResourcesException("cannot harvest natural resource : insufficient iron")
        }

        naturalResources.iron -= harvestIronRate;

        indicators.bio.value -= constants.iron.BIO_MODIFYER
        indicators.bio.evolution -= constants.iron.BIO_EVO_MODIFYER

        indicators.air.evolution -= constants.iron.AIR_EVO_MODIFYER
        indicators.air.value -= constants.iron.AIR_MODIFYER

        indicators.soil.evolution -= constants.iron.SOIL_EVO_MODIFYER
        indicators.soil.value -= constants.iron.SOIL_MODIFYER

        if (abs(Random.nextInt()) % 100 < (constants.iron.HARVEST_PROBA)) {

            resourceStorage.iron.addToStorage(harvestIronValue)

        }

        return this
    }

}
