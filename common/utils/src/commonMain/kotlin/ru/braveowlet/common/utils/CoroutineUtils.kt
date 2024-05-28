package ru.braveowlet.common.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch

fun <T> Flow<T>.retryIfError(): Flow<T> =
    retryWhen { _, _ -> true }

fun supervisorHandler(onError: (Throwable) -> Unit) =
    CoroutineExceptionHandler { _, t -> onError(t) } + SupervisorJob()

suspend fun supervisorLaunch(
    onError: (Throwable) -> Unit,
    block: suspend () -> Unit,
) = coroutineScope {
    launch(
        context = supervisorHandler(onError),
        block = { block() }
    )
}
