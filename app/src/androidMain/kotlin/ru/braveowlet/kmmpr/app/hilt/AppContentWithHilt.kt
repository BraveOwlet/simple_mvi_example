package ru.braveowlet.kmmpr.app.hilt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppContentWithHilt() {
    val appViewModel : AppViewModelWithHilt = hiltViewModel()
    val mainScreenFeatureApi = remember { appViewModel.mainScreenFeatureApi }
    mainScreenFeatureApi.mainScreen()
}