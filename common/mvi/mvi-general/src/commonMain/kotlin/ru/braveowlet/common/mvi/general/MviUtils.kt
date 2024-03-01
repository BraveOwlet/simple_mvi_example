package ru.braveowlet.common.mvi.general

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

internal fun getRandomInstanceId(length: UInt): String {
    val checkedLength = length.toInt()
    val maxRange = 10f.pow(checkedLength).toInt()
    var random = Random.nextInt(1..<maxRange).toString()
    val count = checkedLength - random.length
    repeat(count) {
        random = "0$random"
    }
    return random
}

internal fun <T> Flow<T>.launchIn(
    coroutineScope: CoroutineScope,
    coroutineContext: CoroutineContext
) {
    coroutineScope.launch(coroutineContext) {
        collect()
    }
}

internal fun <T> Flow<T>.logCatch(
    tag: String,
    log: (String)->Unit,
) = catch { log("$tag -> $it") }

internal fun <T> Flow<T>.logEach(
    tag: String,
    log: (String)->Unit,
) = onEach { log("$tag -> $it") }
