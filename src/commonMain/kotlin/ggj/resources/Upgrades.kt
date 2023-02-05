package ggj.resources

import kotlinx.serialization.Serializable

val woodUpgrades = mapOf(
    Pair("w1", Pair(Price(500, 50, 200, 60f), WoodUpgrade(2000, 0))),
    Pair("w2", Pair(Price(1000, 150, 400, 180f), WoodUpgrade( 4000, 0))),
    Pair("w3", Pair(Price(2500, 800, 200, 600f), WoodUpgrade( 10000, 0))),
    Pair("w4", Pair(Price(5000, 1500, 500, 1800f), WoodUpgrade( 14000, 0))),
    Pair("w5", Pair(Price(8000, 300, 1000, 3600f), WoodUpgrade(20000, 0))),
)
val fruitsUpgrades = mapOf(
    Pair("f1", Pair(Price(500, 50, 200, 60f), FruitsUpgrade(2000, 0))),
    Pair("f3", Pair(Price(1000, 150, 400, 180f), FruitsUpgrade( 4000, 0))),
    Pair("f3", Pair(Price(2500, 800, 200, 600f), FruitsUpgrade( 10000, 0))),
    Pair("f4", Pair(Price(5000, 1500, 500, 1800f), FruitsUpgrade( 14000, 0))),
    Pair("f5", Pair(Price(8000, 300, 1000, 3600f), FruitsUpgrade( 20000, 0))),
)
val ironUpgrades = mapOf(
    Pair("i1", Pair(Price(500, 50, 200, 60f), IronUpgrade(2000, 0))),
    Pair("i2", Pair(Price(1000, 150, 400, 180f), IronUpgrade( 4000, 0))),
    Pair("i3", Pair(Price(2500, 800, 200, 600f), IronUpgrade( 10000, 0))),
    Pair("i4", Pair(Price(5000, 1500, 500, 1800f), IronUpgrade( 14000, 0))),
    Pair("i5", Pair(Price(8000, 300, 1000, 3600f), IronUpgrade( 20000, 0))),
)
@Serializable
class Upgrades() {

}