package ru.braveowlet.kmmpr.app

import ru.braveowlet.kmmpr.components.dogs.di.dogsModule
import ru.braveowlet.kmmpr.core.database.databaseModule
import ru.braveowlet.kmmpr.core.database.queriesModule
import ru.braveowlet.kmmpr.core.network.networkModule
import ru.braveowlet.kmmpr.features.dogs_screen.impl.dogsScreenModule
import ru.braveowlet.kmmpr.features.main_screen.impl.mainScreenModule
import ru.braveowlet.kmmpr.features.resources_screen.impl.resourcesScreenModule
import ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.savedDogsScreenModule

private val coreModules
    get() = listOf(
        networkModule,
        databaseModule,
        queriesModule,
    )

private val componentsModules
    get() = listOf(
        dogsModule,
    )

private val featureModules
    get() = listOf(
        mainScreenModule,
        dogsScreenModule,
        savedDogsScreenModule,
        resourcesScreenModule,
    )

val appModules
    get() = listOf(
        coreModules,
        componentsModules,
        featureModules,
    ).flatten()
