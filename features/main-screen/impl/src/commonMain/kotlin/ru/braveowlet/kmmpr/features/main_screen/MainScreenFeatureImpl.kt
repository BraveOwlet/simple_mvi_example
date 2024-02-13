package ru.braveowlet.kmmpr.features.main_screen

import androidx.compose.runtime.Composable
import ru.braveowlet.kmmpr.common.mvi.MviViewModel
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenViewModel

class MainScreenFeatureImpl : MainScreenFeatureApi {

    @Composable
    override fun mainScreen() = MainScreen()

    override val viewModel: MviViewModel<*, *, *, *>
        get() = MainScreenViewModel()
}