package ru.braveowlet.common.utils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object ThreadUtils {
    actual var getThreadName: () -> String = { Thread.currentThread().name }
}
