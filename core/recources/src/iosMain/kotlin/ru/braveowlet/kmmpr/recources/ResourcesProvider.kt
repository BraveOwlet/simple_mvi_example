package ru.braveowlet.kmmpr.recources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource as mokoPainterResource
import dev.icerock.moko.resources.desc.desc
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun getStringResource(stringResource : StringResource) : String =
    stringResource.desc().localized()

@Composable
actual fun getImageResource(imageResource : ImageResource) : Painter =
    mokoPainterResource(imageResource)

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun getImageResource(imageResource : String) : Painter =
    painterResource(imageResource)
