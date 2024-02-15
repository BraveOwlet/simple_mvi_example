package ru.braveowlet.kmmpr.features.main_screen.impl

import androidx.compose.runtime.Composable
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi

class MainScreenFeatureImpl : MainScreenFeatureApi {

    @Composable
    override fun mainScreen() = MainScreen()
}