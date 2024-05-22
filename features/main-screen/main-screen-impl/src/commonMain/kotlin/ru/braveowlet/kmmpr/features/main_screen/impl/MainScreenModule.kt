package ru.braveowlet.kmmpr.features.main_screen.impl

import org.koin.dsl.module
import ru.braveowlet.common.mvi.impl.createFactoryModel
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.MainScreen
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.MainScreenModel

val mainScreenModule
    get() = module {
        createFactoryModel<MainScreen> { tag -> MainScreenModel(tag) }
        single<MainScreenApi> { MainScreenImpl() }
    }
