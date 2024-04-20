package ru.braveowlet.kmmpr.features.main_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.model.MainScreenModel

val mainScreenModule
    get() = module {
        val tag = MainScreenApi.TAG
        factory<MviModel<*, *, *, *>>(qualifier(tag)) { MainScreenModel(tag) }
        single<MainScreenApi> { MainScreenImpl(tag) }
    }
