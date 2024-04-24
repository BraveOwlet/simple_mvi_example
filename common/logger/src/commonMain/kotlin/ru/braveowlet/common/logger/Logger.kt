package ru.braveowlet.common.logger

interface Logger {
    fun log(tag: String, message: String)
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object DefaultLogger : Logger{
    override fun log(tag: String, message: String)
}

