package ru.braveowlet.kmmpr.common.mvi

interface MviLogger {
    fun log(tag: String, message: String)
}

object MviLoggerDefault : MviLogger {
    override fun log(tag: String, message: String) {
        println("MVI_$tag $message")
    }
}
