package ggj

import kotlin.test.Test
import kotlin.test.assertEquals

internal class StoredResourceTest {

    @Test
    internal fun testWoodProduction() {

        val woodResource = WoodResource(0.0f, 100.0f)

        assertEquals(0.0f, woodResource.quantity)

        woodResource.produce(5.2f)
        assertEquals(5.2f, woodResource.quantity)

    }
    @Test
    internal fun testWoodMaxCapacity() {

        val woodResource = WoodResource(98.0f, 100.0f)

        assertEquals(98.0f, woodResource.quantity)

        woodResource.produce(3.0f)
        assertEquals(100.0f, woodResource.quantity)

    }


}