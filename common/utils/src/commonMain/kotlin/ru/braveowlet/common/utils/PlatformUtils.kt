package ru.braveowlet.common.utils

enum class PlatformType {
    Android,
    Ios,
}

data class Platform(
    val type: PlatformType,
    val name: String,
    val version: String,
)

expect val platform: Platform
