package ru.braveowlet.common.utils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object ThreadUtils {
    var getThreadName: () -> String
}
