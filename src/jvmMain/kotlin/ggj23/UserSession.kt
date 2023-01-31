package ggj23

import ggj.User
import kotlinx.serialization.Serializable

@Serializable
data class UserSession(val user: User)
