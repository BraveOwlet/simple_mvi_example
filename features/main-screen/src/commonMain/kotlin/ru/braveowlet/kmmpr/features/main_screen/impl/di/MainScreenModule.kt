package ru.braveowlet.kmmpr.features.main_screen.impl.di

import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenModel

val mainScreenModule = module {
    factory { MainScreenModel() }
}
