package ru.braveowlet.kmmpr.features.main_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreenModel
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenModel

val mainScreenModule = module {
    factory<MviScreenModel<*, *, *, *>>(qualifier(MainScreenModel.tag)) { MainScreenModel() }
    single<MainScreenApi> { MainScreenImpl() }
}
