package ru.braveowlet.common.logger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object DefaultLogger : Logger {
    actual override fun log(tag: String, message: String) {
        println("$TAG_PREFIX$tag $message")
    }
}
