package ggj.items

import kotlinx.serialization.Serializable

@Serializable
class ItemsStorage {

    val seeds = mutableListOf<Seed>()

    fun allItems() : List<Item> {
        val items = mutableListOf<Item>()
        items.addAll(seeds)
        return items
    }
}