package ru.braveowlet.common.utils

import android.os.Build

@Suppress("unused")
actual val platform: Platform = Platform(
    type = PlatformType.Android,
    name = "Android",
    version = "${Build.VERSION.SDK_INT}"
)
