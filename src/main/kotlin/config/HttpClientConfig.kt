package config

import dev.inmo.tgbotapi.types.MilliSeconds
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import java.net.*
import java.util.*
import java.util.concurrent.TimeUnit

@Serializable
data class HttpClientConfig(
    val connectionTimeoutMillis: MilliSeconds? = null,
    val requestTimeoutMillis: MilliSeconds? = null,
    val responseTimeoutMillis: MilliSeconds? = null,
    val proxy: ProxyConfig? = null
) {
    @Serializable
    data class ProxyConfig(
        val hostname: String,
        val type: ProxyType = ProxyType.socks,
        val port: Int = type.defaultPort,
        val username: String? = null,
        val password: String? = null
    ) {
        @Serializable
        enum class ProxyType(val defaultPort: Int, val proxyType: Proxy.Type) {
            socks(1080, Proxy.Type.SOCKS),
            http(3128, Proxy.Type.HTTP),
        }

        val socketAddress
            get() = InetSocketAddress(hostname, port)
    }

    fun HttpClientConfig<OkHttpConfig>.setupConfig() {
        // setting up telegram bot client
        engine {
            // Start setup bot client engine configuration
            config {
                // setting up connection timeout millis
                connectionTimeoutMillis ?.let { connectTimeout(it, TimeUnit.MILLISECONDS) }
                // setting up write timeout millis
                requestTimeoutMillis ?.let { writeTimeout(it, TimeUnit.MILLISECONDS) }
                // setting up read timeout millis
                responseTimeoutMillis ?.let { readTimeout(it, TimeUnit.MILLISECONDS) }

                // Start setup bot client engine proxy
                this@HttpClientConfig.proxy ?.let { proxyConfig ->
                    proxy(
                        Proxy(
                            proxyConfig.type.proxyType,
                            proxyConfig.socketAddress
                        )
                    )

                    proxyConfig.username ?.let { username ->
                        when (proxyConfig.type) {
                            config.HttpClientConfig.ProxyConfig.ProxyType.socks -> {
                                val passwordAuthentication = PasswordAuthentication(
                                    username,
                                    proxyConfig.password ?.toCharArray() ?: error("For Socks proxy you should use both username and password or do not use authentication at all")
                                )
                                Authenticator.setDefault(object : Authenticator() {
                                    override fun getPasswordAuthentication(): PasswordAuthentication? {
                                        return if (requestingHost.lowercase() == proxyConfig.hostname.lowercase()) {
                                            passwordAuthentication
                                        } else {
                                            null
                                        }
                                    }
                                })
                            }
                            config.HttpClientConfig.ProxyConfig.ProxyType.http -> {
                                val passwordSuffix = proxyConfig.password ?.let { ":$it" }
                                val credentials = Base64.getEncoder().encodeToString("${username}${passwordSuffix}".toByteArray())
                                this@setupConfig.defaultRequest {
                                    header(HttpHeaders.ProxyAuthorization, "Basic $credentials")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
