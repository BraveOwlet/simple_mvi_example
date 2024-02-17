package ru.braveowlet.kmmpr.core.network

import io.ktor.client.HttpClient
import org.koin.dsl.module

fun ktorModule() = module {
    single<HttpClient> { createKtorClient() }
}