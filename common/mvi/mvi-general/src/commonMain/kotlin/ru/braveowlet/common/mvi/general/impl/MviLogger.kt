package ru.braveowlet.common.mvi.general.impl

import ru.braveowlet.common.logger.DefaultLogger
import ru.braveowlet.common.logger.getThreadName
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.models.MviState
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

internal abstract class MviLogger<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String,
    private val logEnable: Boolean,
) {
    private val instanceId: String = getMviInstanceId()

    private val threadName: String get() = getThreadName()

    private fun log(tag: String, message: String, error: Throwable?) =
        logMessage { (tag + ERROR_TAG.takeIf { error != null }.orEmpty()) + " -> $message" }

    private fun logMessage(message: () -> String) {
        if (logEnable) {
            DefaultLogger.log("$MVI_TAG$tag [$instanceId]", "[$threadName] ${message()}")
        }
    }

    protected fun customLog(customLogEnable: Boolean = false, message: () -> String){
        if (customLogEnable) {
            DefaultLogger.log("$MVI_TAG$tag [$instanceId]", "[$threadName] ${message()}")
        }
    }

    protected fun log(state: State, error: Throwable? = null) {
        if (logEnable && state.isLoggable) {
            log(STATE_TAG, state.toString(), error)
        }
    }

    protected fun log(action: Action, error: Throwable? = null) =
        log(ACTION_TAG, action.toString(), error)

    protected fun log(effect: Effect, error: Throwable? = null) =
        log(EFFECT_TAG, effect.toString(), error)

    protected fun log(effect: Event, error: Throwable? = null) =
        log(EVENT_TAG, effect.toString(), error)

    protected fun logErrorBootstrap(error: Throwable? = null) =
        log(BOOTSTRAP_TAG, "", error)

    protected fun logErrorActor(error: Throwable? = null) =
        log(ACTOR_TAG, "", error)

    protected fun logErrorReducer(error: Throwable? = null) =
        log(REDUCER_TAG, "", error)

    protected fun logErrorEvent(error: Throwable? = null) =
        log(EVENT_TAG, "", error)

    protected fun logErrorState(error: Throwable? = null) =
        log(STATE_TAG, "", error)

    private fun getMviInstanceId(
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

    companion object {
        private const val MVI_TAG = "MVI_"
        private const val ERROR_TAG = "_ERROR"
        private const val ACTION_TAG = "ACTION"
        private const val EFFECT_TAG = "EFFECT"
        private const val EVENT_TAG = "EVENT"
        private const val BOOTSTRAP_TAG = "BOOTSTRAP_COMPLIED"
        private const val ACTOR_TAG = "ACTOR"
        private const val REDUCER_TAG = "ACTOR"
        private const val STATE_TAG = "STATE"
        private const val DEFAULT_INSTANCE_ID_LENGTH = 4U
    }
}

