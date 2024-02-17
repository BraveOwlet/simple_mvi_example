package ru.braveowlet.kmmpr.common.logger

import ru.braveowlet.kmmpr.common.logger.Logger
import ru.braveowlet.kmmpr.common.logger.TAG_PREFIX

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object DefaultLogger : Logger {
    override fun log(tag: String, message: String) {
        println("$TAG_PREFIX$tag $message")
    }
}
