package ru.braveowlet.kmmpr.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.braveowlet.kmmpr.common.logger.DefaultLogger

internal fun createKtorClient(): HttpClient = HttpClient{
    install(HttpTimeout){
        requestTimeoutMillis = 15_000
    }
    install(ContentNegotiation){
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                DefaultLogger.log("NETWORK", message)
            }
        }
    }
}
