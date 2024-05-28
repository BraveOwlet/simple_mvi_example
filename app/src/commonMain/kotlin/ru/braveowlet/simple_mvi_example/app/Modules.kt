package ru.braveowlet.simple_mvi_example.app

import ru.braveowlet.simple_mvi_example.components.dogs.di.dogsModule
import ru.braveowlet.simple_mvi_example.core.database.databaseModule
import ru.braveowlet.simple_mvi_example.core.network.networkModule
import ru.braveowlet.simple_mvi_example.features.dog_screens.impl.dogScreensModule
import ru.braveowlet.simple_mvi_example.features.main_screen.impl.mainScreenModule

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
