package ru.braveowlet.kmmpr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.braveowlet.kmmpr.features.main_screen.di.MainScreenFeature
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    @MainScreenFeature val mainScreenFeatureApi: MainScreenFeatureApi
) : ViewModel()

@Composable
fun AppWithHilt() {
    val appViewModel : AppViewModel = hiltViewModel()
    val mainScreenFeatureApi = remember { appViewModel.mainScreenFeatureApi }
    mainScreenFeatureApi.mainScreen()
}