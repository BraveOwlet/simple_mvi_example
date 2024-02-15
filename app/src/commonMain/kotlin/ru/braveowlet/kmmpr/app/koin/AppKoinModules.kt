package ru.braveowlet.kmmpr.app.koin

import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin() {
    startKoin {
        modules(appModules())
    }
}

expect fun appModules(): List<Module>
