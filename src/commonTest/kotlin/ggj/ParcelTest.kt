package ggj

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val TOLERANCE = 0.00001f
private const val TOLERANCE_SEED = 0.01

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
        assertEquals(0.049f, parcel.indicators.bio.evolution, TOLERANCE)

        parcel.naturalResources.fruits = 0f
        assertFailsWith<InsufficientNaturalResourcesException> {
            parcel.harvestFruits()
        }

    }

    @Test
    internal fun testSeedLootRate() {
        val parcel = Parcel()
        val iteration = 1000000
        parcel.naturalResources.trees = iteration.toFloat();

        for (i in 1..iteration) {
            parcel.harvestWood()
        }

        assertEquals(0.05, parcel.items.size / iteration.toDouble(), TOLERANCE_SEED)

    }
}