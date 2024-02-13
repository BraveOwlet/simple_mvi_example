package ru.braveowlet.kmmpr

import android.app.Application
import koinMviControllersModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComposeApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComposeApp)
            modules(koinMviControllersModules())
        }
    }
}