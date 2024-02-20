package ru.braveowlet.kmmpr.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> HttpClient.fetch(
    block: HttpRequestBuilder.() -> Unit
): NetworkResult<T> = try {
    val response = request(block)
    if (response.status == HttpStatusCode.OK)
        NetworkResult.Success(response.body())
    else
        NetworkResult.Error(Throwable("${response.status}: ${response.bodyAsText()}"))
} catch (e: Exception) {
    NetworkResult.Error(e)
}

suspend inline fun <reified T> HttpClient.fetchForGet(
    url: String,
): NetworkResult<T> = try {
    val response = get(url)
    if (response.status == HttpStatusCode.OK)
        NetworkResult.Success(response.body())
    else
        NetworkResult.Error(Throwable("${response.status}: ${response.bodyAsText()}"))
} catch (e: Exception) {
    NetworkResult.Error(e)
}

sealed interface NetworkResult<out R> {
    data class Success<out R>(val value: R) : NetworkResult<R>
    data class Error(val throwable: Throwable) : NetworkResult<Nothing>
}

inline fun <reified R> NetworkResult<R>.takeIfSuccess(): R? =
    if (this is NetworkResult.Success) value else null

inline fun <reified R, reified T> NetworkResult<R>.map(mapper: (R) -> T): NetworkResult<T> =
    when (this) {
        is NetworkResult.Error -> NetworkResult.Error(this.throwable)
        is NetworkResult.Success -> NetworkResult.Success(mapper(this.value))
    }
