package ggj

import kotlin.test.Test
import kotlin.test.assertEquals

internal class StoredResourceTest {

    @Test
    internal fun testWoodProduction() {
        val storedResource = StoredResource(0.4f, 100.0f)

        assertEquals(0.4f, storedResource.quantity)

        storedResource.addToStorage(5.2f)
        assertEquals(5.6f, storedResource.quantity)
    }

    @Test
    internal fun testWoodMaxCapacity() {
        val storedResource = StoredResource(98.3f, 100.0f)

        storedResource.addToStorage(4.2f)
        assertEquals(100.0f, storedResource.quantity)

    }
}