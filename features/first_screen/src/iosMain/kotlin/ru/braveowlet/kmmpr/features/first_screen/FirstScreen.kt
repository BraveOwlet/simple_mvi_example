package ru.braveowlet.kmmpr.features.first_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import ru.braveowlet.kmmpr.features.first_screen.compose.FirstScreenContent
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenController

@Composable
actual fun FirstScreen() {
    val controller = remember { ScreenController() }
    FirstScreenContent(controller)
}
