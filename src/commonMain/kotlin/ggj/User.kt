package ggj

import kotlinx.serialization.Serializable

@Serializable
data class User(val userId: Int, val userName: String)
