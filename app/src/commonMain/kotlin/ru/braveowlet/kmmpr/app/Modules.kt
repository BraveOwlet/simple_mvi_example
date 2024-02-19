package ru.braveowlet.kmmpr.app

import ru.braveowlet.kmmpr.features.dogs_screen.impl.dogsScreenModule
import ru.braveowlet.kmmpr.features.main_screen.impl.mainScreenModule
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.savedDogsScreenModule

private val featureModules = listOf(
    mainScreenModule,
    dogsScreenModule,
    savedDogsScreenModule,
)

val appModules = listOf(
    featureModules
).flatten()
