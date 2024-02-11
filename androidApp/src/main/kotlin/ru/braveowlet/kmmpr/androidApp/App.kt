package ru.braveowlet.kmmpr.androidApp

import android.app.Application
import koinMviControllersModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinMviControllersModules())
        }
    }
}