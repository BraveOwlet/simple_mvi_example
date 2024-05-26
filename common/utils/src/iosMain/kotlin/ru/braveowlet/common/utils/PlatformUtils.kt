package ru.braveowlet.common.utils

import platform.UIKit.UIDevice

@Suppress("unused")
actual val platform: Platform = Platform(
    type = PlatformType.Ios,
    name = UIDevice.currentDevice.systemName(),
    version = UIDevice.currentDevice.systemVersion
)
