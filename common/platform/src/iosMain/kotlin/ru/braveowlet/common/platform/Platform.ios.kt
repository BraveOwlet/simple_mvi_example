package ru.braveowlet.common.platform

import platform.UIKit.UIDevice
import ru.braveowlet.common.platform.Platform
import ru.braveowlet.common.platform.PlatformType

actual val platform : Platform = Platform(
    type = PlatformType.Ios,
    name = UIDevice.currentDevice.systemName(),
    version = UIDevice.currentDevice.systemVersion
)