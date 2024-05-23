package ru.braveowlet.kmmpr.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger as KtorLogger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.braveowlet.common.logger.LogType
import ru.braveowlet.common.logger.Logger

internal fun createKtorClient(): HttpClient = HttpClient {
    install(HttpTimeout) {
        requestTimeoutMillis = 15_000
    }
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : KtorLogger {
            override fun log(message: String) {
                Logger.log(
                    message = message,
                    type = LogType.NETWORK,
                )
            }
        }
    }
}
