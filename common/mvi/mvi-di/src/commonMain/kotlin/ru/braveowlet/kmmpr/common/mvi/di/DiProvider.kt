package ru.braveowlet.kmmpr.common.mvi.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalDi = compositionLocalOf<DiType?> { null }

@Composable
fun DiProvider(
    diType: DiType,
    content: @Composable () -> Unit
) = CompositionLocalProvider(LocalDi provides diType, content)
