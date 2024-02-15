package ru.braveowlet.kmmpr.app.compose

import androidx.compose.runtime.Composable
import org.koin.compose.koinInject
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi

@Composable
fun AppContentWithKoin() {
    val mainScreenFeatureApi = koinInject<MainScreenFeatureApi>()
    mainScreenFeatureApi.mainScreen()
}