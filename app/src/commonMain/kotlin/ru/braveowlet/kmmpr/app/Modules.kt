package ru.braveowlet.kmmpr.app

import ru.braveowlet.kmmpr.features.main_screen.impl.di.mainScreenModule

private val featureModules = listOf(
    mainScreenModule
)

val appModules = listOf(
    featureModules
).flatten()
