package ru.braveowlet.kmmpr.common.mvi.di

import ru.braveowlet.kmmpr.common.platform.PlatformType
import ru.braveowlet.kmmpr.common.platform.platform

enum class DiType {
    KOIN, HILT;
}

fun DiType?.getByPlatform() = when (this) {
    null -> when (platform.type) {
        PlatformType.Android -> DiType.HILT
        PlatformType.Ios -> DiType.KOIN
    }
    DiType.KOIN, DiType.HILT -> this
}