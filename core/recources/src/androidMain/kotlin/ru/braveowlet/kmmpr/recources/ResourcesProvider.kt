package ru.braveowlet.kmmpr.recources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import dev.icerock.moko.resources.compose.painterResource as mokoPainterResource

@Composable
actual fun getStringResource(stringResource: StringResource): String =
    stringResource(id = stringResource.resourceId)

@Composable
actual fun getImageResource(imageResource: ImageResource): Painter =
    mokoPainterResource(imageResource)

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun getImageResource(imageResource: String): Painter =
    painterResource(imageResource)
