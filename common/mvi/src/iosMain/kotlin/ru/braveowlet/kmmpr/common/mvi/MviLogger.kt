package ru.braveowlet.kmmpr.common.mvi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object MviLoggerDefault : MviLogger {
    override fun log(tag: String, message: String) {
        println("MVI_$tag $message")
    }
}
