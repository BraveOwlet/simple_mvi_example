package ru.braveowlet.kmmpr.app

import ru.braveowlet.kmmpr.components.dogs.di.dogsModule
import ru.braveowlet.kmmpr.core.database.databaseModule
import ru.braveowlet.kmmpr.core.network.networkModule
import ru.braveowlet.kmmpr.features.dog_screens.impl.dogScreensModule
import ru.braveowlet.kmmpr.features.main_screen.impl.mainScreenModule

private val coreModules
    get() = listOf(
        networkModule,
        databaseModule,
    )

private val componentsModules
    get() = listOf(
        dogsModule,
    )

private val featureModules
    get() = listOf(
        mainScreenModule,
        dogScreensModule,
    )

val appModules
    get() = listOf(
        coreModules,
        componentsModules,
        featureModules,
    ).flatten()
