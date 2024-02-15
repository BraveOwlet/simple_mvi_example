package ru.braveowlet.kmmpr.common.platform

import platform.UIKit.UIDevice

actual val platform : Platform = Platform(
    type = PlatformType.Ios,
    name = UIDevice.currentDevice.systemName(),
    version = UIDevice.currentDevice.systemVersion
)