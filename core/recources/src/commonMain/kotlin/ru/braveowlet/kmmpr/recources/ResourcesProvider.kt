package ru.braveowlet.kmmpr.recources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

@Composable
expect fun getStringResource(stringResource : StringResource) : String

@Composable
expect fun getImageResource(imageResource : ImageResource) : Painter

@Composable
expect fun getImageResource(imageResource : String) : Painter
