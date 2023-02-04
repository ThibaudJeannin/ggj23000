package ggj

import ggj.resources.Production
import ggj.resources.ResourceStorage
import kotlin.test.Test
import kotlin.test.assertEquals

class ResourceStorageTest {

    @Test
    fun testStorageProduction() {
        val resourceStorage = ResourceStorage()
        assertEquals(0.0f, resourceStorage.wood.quantity)
        assertEquals(0.0f, resourceStorage.fruits.quantity)
        assertEquals(0.0f, resourceStorage.iron.quantity)

        resourceStorage.addProduction(Production(2.3f, 4.5f, 6.7f))
        assertEquals(2.3f, resourceStorage.wood.quantity)
        assertEquals(4.5f, resourceStorage.fruits.quantity)
        assertEquals(6.7f, resourceStorage.iron.quantity)
    }

}