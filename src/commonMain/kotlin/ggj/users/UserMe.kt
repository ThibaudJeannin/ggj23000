package ggj.users

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class UserMe(val publicUser: User, val userTag: String = randomTag()) {
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
        return "${this.publicUser.userName}#${this.userTag} (${this.publicUser.userId})"
    }
}