package ru.braveowlet.kmmpr.common.platform

data class Platform(
    val type: PlatformType,
    val name: String,
    val version: String,
)

expect val platform : Platform