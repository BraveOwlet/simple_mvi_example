package ru.braveowlet.kmmpr.common.mvi

interface MviLogger {
    fun log(tag: String, message: String)
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object MviLoggerDefault : MviLogger
