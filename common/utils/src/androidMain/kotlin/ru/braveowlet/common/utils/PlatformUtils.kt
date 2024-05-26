package ru.braveowlet.common.utils

import android.os.Build

actual val platform: Platform = Platform(
    type = PlatformType.Android,
    name = "Android",
    version = "${Build.VERSION.SDK_INT}"
)
