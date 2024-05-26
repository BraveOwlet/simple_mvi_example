package ru.braveowlet.kmmpr.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.braveowlet.common.logger.Logger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModules
            )
        }
        initApp()
    }
}

fun initApp() {
    Logger.init()
}
