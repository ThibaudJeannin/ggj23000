package ggj

import kotlin.test.Test
import kotlin.test.assertEquals

internal class WoodResourceTest {

    private val indicators = Indicators(0.5f, 0.8f, 0.2f)
    private val naturalResources = NaturalResources(10.0f, 5.0f)

    @Test
    internal fun testWoodProduction() {

        val woodResource = WoodResource(0.0f, 100.0f)

        assertEquals(0.0f, woodResource.quantity)

        woodResource.produce(indicators, naturalResources)
        assertEquals(5.0f, woodResource.quantity)

    }

    @Test
    internal fun testWoodMaxCapacity() {

        val woodResource = WoodResource(98.0f, 100.0f)

        assertEquals(98.0f, woodResource.quantity)

        woodResource.produce(Indicators(bio = 1.0f), NaturalResources(trees = 5.0f))
        assertEquals(100.0f, woodResource.quantity)

    }
}