package ru.braveowlet.kmmpr

import android.app.Application
import appModules
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class ComposeApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComposeApp)
            modules(appModules())
        }
    }
}
