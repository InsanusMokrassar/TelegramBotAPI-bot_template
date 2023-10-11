package config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val token: String,
    val client: HttpClientConfig? = null
)
