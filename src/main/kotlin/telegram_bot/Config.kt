package telegram_bot

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val token: String
)
