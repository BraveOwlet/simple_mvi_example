package ru.braveowlet.common.mvi.general.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.braveowlet.common.logger.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

internal fun CoroutineScope.launchWithLogException(
    tag: String,
    dispatcher: CoroutineDispatcher,
    onError: (String) -> Unit,
    block: suspend CoroutineScope.() -> Unit
) = launch(
    context = dispatcher + CoroutineExceptionHandler { _, error -> onError("$tag -> $error") },
    block = block
)

internal fun getRandomInstanceId(
    length: UInt = DEFAULT_INSTANCE_ID_LENGTH
): String {
    val checkedLength = length.toInt()
    val maxRange = 10f.pow(checkedLength).toInt()
    var random = Random.nextInt(1..<maxRange).toString()
    val count = checkedLength - random.length
    repeat(count) {
        random = "0$random"
    }
    return random
}

internal fun String.asErrorTag() = this + "_ERROR"

internal fun String.asMviTag(instanceId: String) = "MVI_$this [$instanceId]"

internal fun String.addMessage(message: Any) = "$this -> $message"

private const val DEFAULT_INSTANCE_ID_LENGTH = 4U

