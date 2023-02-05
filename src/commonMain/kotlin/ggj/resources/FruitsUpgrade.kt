package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class FruitsUpgrade(var storage: Int = 1000, var production: Int = 0): Upgrade() {

}