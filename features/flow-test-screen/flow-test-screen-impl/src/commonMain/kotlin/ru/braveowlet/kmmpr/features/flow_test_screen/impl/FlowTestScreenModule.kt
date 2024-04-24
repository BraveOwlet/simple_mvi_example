package ru.braveowlet.kmmpr.features.flow_test_screen.impl

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.braveowlet.common.mvi.impl.MviModel
import ru.braveowlet.kmmpr.features.flow_test_screen.api.FlowTestScreenApi
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.FlowTestScreenModel

val flowTestScreenModule
    get() = module {
        val tag = FlowTestScreenApi.TAG
        factory<MviModel<*, *, *, *>>(qualifier(tag)) { FlowTestScreenModel(tag) }
        single<FlowTestScreenApi> { FlowTestScreenImpl(tag) }
    }
