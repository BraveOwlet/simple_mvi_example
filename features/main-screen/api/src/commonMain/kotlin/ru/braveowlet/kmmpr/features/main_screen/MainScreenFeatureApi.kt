package ru.braveowlet.kmmpr.features.main_screen

import androidx.compose.runtime.Composable
import ru.braveowlet.kmmpr.common.mvi.MviViewModel

interface MainScreenFeatureApi {

    @Composable
    fun mainScreen()

    val viewModel : MviViewModel<*,*,*,*>
}