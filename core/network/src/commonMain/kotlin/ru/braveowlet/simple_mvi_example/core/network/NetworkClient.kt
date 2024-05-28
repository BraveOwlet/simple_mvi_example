package ru.braveowlet.simple_mvi_example.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.braveowlet.common.logger.LogType
import ru.braveowlet.common.logger.Logger
import io.ktor.client.plugins.logging.Logger as KtorLogger

internal fun createKtorClient(): HttpClient = HttpClient {
    install(HttpTimeout) {
        requestTimeoutMillis = 15_000
    }
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }
        )
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
