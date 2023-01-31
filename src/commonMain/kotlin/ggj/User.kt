package ggj

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class User(val userId: String = randomTag(), val userName: String) {
    companion object {
        fun randomTag(): String {
            val tag = StringBuilder()
            for (i in 1..4) {
                tag.append(Random.nextInt(9))
            }
            return tag.toString();
        }
    }

    override fun toString(): String {
        return this.userName + "#" + this.userId
    }
}