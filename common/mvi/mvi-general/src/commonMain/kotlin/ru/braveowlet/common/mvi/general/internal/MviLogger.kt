package ru.braveowlet.common.mvi.general.internal

import ru.braveowlet.common.logger.DefaultLogger
import ru.braveowlet.common.logger.getThreadName
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

internal class MviLogger<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String,
    private val logEnable: Boolean,
) {
    private val instanceId: String = getMviInstanceId()

    private val threadName: String get() = getThreadName()

    private fun log(tag: String, message: String, error: Throwable?) {
        if (logEnable) {
            val messageWithError = error
                ?.let { "${message.takeIf { it.isNotBlank() }?.let { "/$it" } ?: ""}$error" }
                ?: message

            val tagWithMessage = tag +
                    ERROR_TAG.takeIf { error != null }.orEmpty() +
                    " -> $messageWithError"

            DefaultLogger.log(
                "$MVI_TAG${this.tag} [$instanceId]",
                "[$threadName] $tagWithMessage"
            )
        }
    }

    fun log(state: State, error: Throwable? = null) {
        if (logEnable) {
            log(STATE_TAG, state.getLogString(), error)
        }
    }

    fun log(action: Action, error: Throwable? = null) =
        log(ACTION_TAG, action.toString(), error)

    fun log(effect: Effect, error: Throwable? = null) =
        log(EFFECT_TAG, effect.toString(), error)

    fun log(effect: Event, error: Throwable? = null) =
        log(EVENT_TAG, effect.toString(), error)

    fun logBootstrap(error: Throwable? = null) =
        log(BOOTSTRAP_TAG, getCompliedMessage(error), error)

    fun logActor(error: Throwable? = null) =
        log(ACTOR_TAG, getCompliedMessage(error), error)

    fun logReducer(error: Throwable? = null) =
        log(REDUCER_TAG, getCompliedMessage(error), error)

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

    private fun getCompliedMessage(error: Throwable?) = error?.let { "" } ?: COMPLIED_MESSAGE

    companion object {
        private const val MVI_TAG = "MVI_"
        private const val COMPLIED_MESSAGE = "COMPLIED"
        private const val ERROR_TAG = "_ERROR"
        private const val ACTION_TAG = "ACTION"
        private const val EFFECT_TAG = "EFFECT"
        private const val EVENT_TAG = "EVENT"
        private const val BOOTSTRAP_TAG = "BOOTSTRAP"
        private const val ACTOR_TAG = "ACTOR"
        private const val REDUCER_TAG = "REDUCER"
        private const val STATE_TAG = "STATE"
        private const val DEFAULT_INSTANCE_ID_LENGTH = 4U
    }
}

