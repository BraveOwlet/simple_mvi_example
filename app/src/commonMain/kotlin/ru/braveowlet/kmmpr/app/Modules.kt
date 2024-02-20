package ru.braveowlet.kmmpr.app

import ru.braveowlet.kmmpr.components.dogs.di.dogsModule
import ru.braveowlet.kmmpr.core.network.networkModule
import ru.braveowlet.kmmpr.features.dogs_screen.impl.dogsScreenModule
import ru.braveowlet.kmmpr.features.main_screen.impl.mainScreenModule
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.savedDogsScreenModule

private val featureModules = listOf(
    mainScreenModule,
    dogsScreenModule,
    savedDogsScreenModule,
)

private val coreModules = listOf(
    networkModule,
)

private val componentsModules = listOf(
    dogsModule,
)

val appModules = listOf(
    featureModules,
    coreModules,
    componentsModules,
).flatten()