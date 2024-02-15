package ru.braveowlet.kmmpr.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.braveowlet.kmmpr.app.koin.initKoin

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
