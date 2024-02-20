package ru.braveowlet.kmmpr.common.logger

interface Logger {
    fun log(tag: String, message: String)
}

internal const val TAG_PREFIX = "KMMPR_"

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object DefaultLogger : Logger

