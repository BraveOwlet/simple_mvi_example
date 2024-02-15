package ru.braveowlet.kmmpr.features.main_screen.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi
import ru.braveowlet.kmmpr.features.main_screen.impl.MainScreenFeatureImpl
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenViewModel

actual fun mainScreenModule() : Module = module {
    single<MainScreenFeatureApi> { MainScreenFeatureImpl() }
    single<MainScreenViewModel> { MainScreenViewModel() }
}