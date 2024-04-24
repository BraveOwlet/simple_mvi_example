package ru.braveowlet.common.mvi.general.api.models

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class MviCoroutineScope(
    val scope: CoroutineScope,
    val dispatcher: CoroutineDispatcher,
) {
    fun launch(
        onError: (Throwable) -> Unit = {},
        block: suspend CoroutineScope.() -> Unit
    ) = scope.launch(
        context = dispatcher + CoroutineExceptionHandler { _, error -> onError(error) },
        block = block
    )
}

internal fun Flow<*>.launchIn(
    scope: MviCoroutineScope,
    onError: (Throwable) -> Unit = {},
) = scope.launch(onError) {
    collect()
}

internal fun <T> Flow<T>.flowOn(
    scope: MviCoroutineScope,
): Flow<T> = this.flowOn(scope.dispatcher)

