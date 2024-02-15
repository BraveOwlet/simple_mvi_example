package ru.braveowlet.kmmpr.app.koin

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.di.mainScreenModule

actual fun appModules(): List<Module> = listOf(
    androidModule(),
    mainScreenModule(),
)

private fun androidModule() = module {}
