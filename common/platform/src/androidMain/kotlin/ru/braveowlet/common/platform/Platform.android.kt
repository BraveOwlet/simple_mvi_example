package ru.braveowlet.common.platform

import android.os.Build
import ru.braveowlet.common.platform.Platform
import ru.braveowlet.common.platform.PlatformType

actual val platform : Platform = Platform(
    type = PlatformType.Android,
    name = "Android",
    version = "${Build.VERSION.SDK_INT}"
)