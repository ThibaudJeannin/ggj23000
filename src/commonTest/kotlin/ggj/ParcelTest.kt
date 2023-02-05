package ggj

import ggj.resources.InsufficientNaturalResourcesException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val TOLERANCE = 0.00001f
private const val TOLERANCE_SEED = 0.01
private const val TOLERANCE_IRON = 0.01

class ParcelTest {


    @Test
    internal fun testParcelProduction() {
        val parcel = Parcel()
        parcel.produceResources()
    }

    @Test
    internal fun testHarvestWood() {
        val parcel = Parcel()

        parcel.harvestWood()

        assertEquals(9999.0f, parcel.naturalResources.trees)
        assertEquals(10.0f, parcel.resourceStorage.wood.quantity)

        assertEquals(0.98f, parcel.indicators.bio.value, TOLERANCE)
        assertEquals(0.049f, parcel.indicators.bio.evolution, TOLERANCE)

        assertEquals(1.0f, parcel.indicators.air.value, TOLERANCE)
        assertEquals(0.0499f, parcel.indicators.air.evolution, TOLERANCE)

        assertEquals(1.0f, parcel.indicators.soil.value, TOLERANCE)
        assertEquals(0.05f, parcel.indicators.soil.evolution, TOLERANCE)

        parcel.naturalResources.trees = 0f
        assertFailsWith<InsufficientNaturalResourcesException> {
            parcel.harvestWood()
        }

    }

    @Test
    internal fun testHarvestFruits() {
        val parcel = Parcel()

        parcel.harvestFruits()
        assertEquals(2.0f, parcel.resourceStorage.fruits.quantity)

        assertEquals(1f, parcel.indicators.bio.value, TOLERANCE)
        assertEquals(0.049f, parcel.indicators.bio.evolution, TOLERANCE)

        assertEquals(1.0f, parcel.indicators.air.value, TOLERANCE)
        assertEquals(0.05f, parcel.indicators.air.evolution, TOLERANCE)

        assertEquals(1.0f, parcel.indicators.soil.value, TOLERANCE)
        assertEquals(0.05f, parcel.indicators.soil.evolution, TOLERANCE)

        parcel.naturalResources.fruits = 0f

        assertFailsWith<InsufficientNaturalResourcesException> {
            parcel.harvestFruits()
        }

    }
    @Test
    internal fun testHarvestIron() {
        val parcel = Parcel()

        parcel.harvestIron()

        assertEquals(1f, parcel.indicators.bio.value, TOLERANCE)
        assertEquals(0.05f, parcel.indicators.bio.evolution, TOLERANCE)

        assertEquals(1f, parcel.indicators.air.value, TOLERANCE)
        assertEquals(0.048f, parcel.indicators.air.evolution, TOLERANCE)

        assertEquals(0.998f, parcel.indicators.soil.value, TOLERANCE)
        assertEquals(0.049f, parcel.indicators.soil.evolution, TOLERANCE)

        parcel.naturalResources.iron = 0f
        assertFailsWith<InsufficientNaturalResourcesException> {
            parcel.harvestIron()
        }

    }
    @Test
    internal fun testSeedLootRateWood() {
        val parcel = Parcel()
        val iteration = 1000000
        parcel.naturalResources.trees = iteration.toFloat();

        for (i in 1..iteration) {
            parcel.harvestWood()
        }

        assertEquals(0.15, parcel.items.seeds.size / iteration.toDouble(), TOLERANCE_SEED)

    }

    @Test
    internal fun testSeedLootRateFruits() {
        val parcel = Parcel()
        val iteration = 1000000
        parcel.naturalResources.fruits = iteration.toFloat();

        for (i in 1..iteration) {
            parcel.harvestFruits()
        }

        assertEquals(0.30, parcel.items.seeds.size / iteration.toDouble(), TOLERANCE_SEED)

    }

    @Test
    internal fun testIronLootRate() {
        val parcel = Parcel()
        val iteration = 1000000

        parcel.naturalResources.iron = iteration.toFloat()
        parcel.resourceStorage.iron.capacity = Float.POSITIVE_INFINITY

        for (i in 1..iteration) {
            parcel.harvestIron()
        }

        val assertVal = (constants.iron.HARVEST_PROBA / 100f);

        assertEquals(assertVal.toDouble(), parcel.resourceStorage.iron.quantity / iteration.toDouble(), TOLERANCE_IRON)

    }
    @Test
    internal fun testIronCapacityLimit() {
        val parcel = Parcel()
        val iteration = 10000

        parcel.naturalResources.iron = iteration.toFloat()

        for (i in 1..iteration) {
            parcel.harvestIron()
        }

        assertEquals(parcel.resourceStorage.iron.capacity.toDouble(), parcel.resourceStorage.iron.quantity.toDouble(), TOLERANCE_IRON)

    }
}