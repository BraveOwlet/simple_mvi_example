package ru.braveowlet.common.mvi.general

interface MviAction

interface MviEvent

interface MviEffect

interface MviState {
    fun getLogString() = this.toString()
}
