package ggj.resources

import kotlinx.serialization.Serializable

@Serializable
class IronUpgrade(var storage: Int = 1000, var production: Int = 0): Upgrade() {

}