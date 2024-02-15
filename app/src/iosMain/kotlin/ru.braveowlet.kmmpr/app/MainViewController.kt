package ru.braveowlet.kmmpr.app

import androidx.compose.ui.window.ComposeUIViewController
import ru.braveowlet.kmmpr.app.compose.AppContentWithKoin

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController { AppContentWithKoin() }

