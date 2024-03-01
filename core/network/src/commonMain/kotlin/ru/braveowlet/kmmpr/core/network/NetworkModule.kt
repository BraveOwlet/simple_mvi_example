package ru.braveowlet.kmmpr.core.network

import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule get() = module {
    single<HttpClient> { createKtorClient() }
}
