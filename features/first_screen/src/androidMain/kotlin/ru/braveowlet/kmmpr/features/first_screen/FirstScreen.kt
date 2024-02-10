package ru.braveowlet.kmmpr.features.first_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.braveowlet.kmmpr.features.first_screen.compose.FirstScreenContent
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenController
import javax.inject.Inject

@HiltViewModel
class AndroidScreenController @Inject constructor (): ViewModel(){
    val screenController = ScreenController()
}

@Composable
actual fun FirstScreen() {
    val viewModel = hiltViewModel<AndroidScreenController>()
    FirstScreenContent(viewModel.screenController)
}