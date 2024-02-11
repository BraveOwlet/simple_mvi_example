package ru.braveowlet.kmmpr.common.mvi

import android.util.Log

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object MviLoggerDefault : MviLogger {
    override fun log(tag: String, message: String) {
        Log.d("MVI_$tag", message)
    }
}
