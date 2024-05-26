package ru.braveowlet.common.mvi.general.internal

import ru.braveowlet.common.logger.LogType
import ru.braveowlet.common.logger.Logger
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState
import ru.braveowlet.common.utils.ThreadUtils
import ru.braveowlet.common.utils.getRandomStringNumber

internal class MviLogger<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String,
) {
    private val instanceId: String = getRandomStringNumber(DEFAULT_INSTANCE_ID_LENGTH)

    private fun log(tag: String, message: String, error: Throwable?) {
        if (Logger.getEnable(LogType.MVI)) {
            val messageWithError = error
                ?.let { "${message.takeIf { it.isNotBlank() }?.let { "/$it" } ?: ""}$error" }
                ?: message

            val tagWithMessage = tag +
                    ERROR_TAG.takeIf { error != null }?.let { "_$it" }.orEmpty() +
                    " -> $messageWithError"

            Logger.log(
                message = "[${ThreadUtils.getThreadName()}] $tagWithMessage",
                tag = "${this.tag} [$instanceId]",
                type = LogType.MVI
            )
        }
    }

    private fun getErrorOrCompliedMessage(error: Throwable?) =
        error?.let { "" } ?: COMPLIED_TAG

    fun log(state: State, error: Throwable? = null) =
        log(STATE_TAG, state.log, error)

    fun log(action: Action, error: Throwable? = null) =
        log(ACTION_TAG, action.toString(), error)

    fun log(effect: Effect, error: Throwable? = null) =
        log(EFFECT_TAG, effect.toString(), error)

    fun log(effect: Event, error: Throwable? = null) =
        log(EVENT_TAG, effect.toString(), error)

    fun logBootstrap(error: Throwable? = null) =
        log(BOOTSTRAP_TAG, getErrorOrCompliedMessage(error), error)

    fun logActor(error: Throwable? = null) =
        log(ACTOR_TAG, getErrorOrCompliedMessage(error), error)

    fun logUnknown(error: Throwable? = null) =
        log(UNKNOWN_TAG, getErrorOrCompliedMessage(error), error)

    companion object {
        private const val UNKNOWN_TAG = "UNKNOWN"

        private const val COMPLIED_TAG = "COMPLIED"
        private const val ERROR_TAG = "ERROR"

        private const val ACTION_TAG = "ACTION"
        private const val EFFECT_TAG = "EFFECT"
        private const val EVENT_TAG = "EVENT"

        private const val BOOTSTRAP_TAG = "BOOTSTRAP"
        private const val ACTOR_TAG = "ACTOR"
        private const val STATE_TAG = "STATE"

        private const val DEFAULT_INSTANCE_ID_LENGTH = 4U
    }
}

