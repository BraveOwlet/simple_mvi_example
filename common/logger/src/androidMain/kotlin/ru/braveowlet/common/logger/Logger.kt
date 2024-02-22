package ru.braveowlet.common.logger

import android.util.Log
import ru.braveowlet.common.logger.Logger
import ru.braveowlet.common.logger.TAG_PREFIX

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object DefaultLogger : Logger {
    override fun log(tag: String, message: String) {
        Log.d("$TAG_PREFIX$tag", message)
    }
}
