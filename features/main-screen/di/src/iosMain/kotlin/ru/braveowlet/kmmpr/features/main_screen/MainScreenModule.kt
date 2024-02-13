package ru.braveowlet.kmmpr.features.main_screen

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenViewModel

actual fun mainScreenModule() : Module = module {
    single<MainScreenFeatureApi> { MainScreenFeatureImpl() }
    single<MainScreenViewModel> { MainScreenViewModel() }
}