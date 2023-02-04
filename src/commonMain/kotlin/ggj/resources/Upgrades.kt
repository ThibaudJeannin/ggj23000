package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class Upgrades(val resourceStorage: ResourceStorage) {
    val woodUpgrade = listOf<Upgrade>(
        WoodUpgrade(Price(500, 50, 200, 60f),2000, 0),
        WoodUpgrade(Price(1000, 150, 400, 180f), 4000, 0),
        WoodUpgrade(Price(2500, 800, 200, 600f), 10000, 0),
        WoodUpgrade(Price(5000, 1500, 500, 1800f), 14000, 0),
        WoodUpgrade(Price(8000, 300, 1000, 3600f), 20000, 0)
    )
}