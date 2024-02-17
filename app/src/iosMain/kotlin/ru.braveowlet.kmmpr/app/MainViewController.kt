package ru.braveowlet.kmmpr.app

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import org.koin.core.context.startKoin
import ru.braveowlet.kmmpr.features.main_screen.impl.MainScreen

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    Navigator(
        MainScreen()
    )
}

fun initKoin() {
    startKoin {
        modules(
            appModules
        )
    }
}
