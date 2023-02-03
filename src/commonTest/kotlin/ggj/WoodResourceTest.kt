package ggj

import kotlin.test.Test
import kotlin.test.assertEquals

internal class WoodResourceTest {

    @Test
    internal fun testWoodProduction() {
        val woodResource = WoodResource(0.0f)
        assertEquals(0.0f, woodResource.quantity)

        woodResource.produce(5.2f)
        assertEquals(5.2f, woodResource.quantity)
    }
}