package ru.braveowlet.common.ui.design_system.presets.value

import androidx.compose.ui.unit.Dp

data class PaddingPreset(
    override val extraSmall: Dp,
    override val small: Dp,
    override val medium: Dp,
    override val large: Dp,
    override val extraLarge: Dp
) : ValuePreset<Dp>