package ru.braveowlet.kmmpr.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import ru.braveowlet.common.logger.Logger
import ru.braveowlet.common.utils.ThreadUtils
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    val mainScreenFeatureApi = koinInject<MainScreenApi>()
    MaterialTheme {
        Navigator(
            mainScreenFeatureApi.mainScreen()
        )
    }
}

@Suppress("unused")
fun initApp(
    iosUtils: IosUtils
) {
    initKoin()
    Logger.init()
    ThreadUtils.init(iosUtils::getThreadName)
}

private fun initKoin() {
    startKoin {
        modules(
            appModules
        )
    }
}
