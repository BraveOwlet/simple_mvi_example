package ru.braveowlet.common.ui.design_system.presets.value

data class AlphaPreset(
    override val extraSmall: Float,
    override val small: Float,
    override val medium: Float,
    override val large: Float,
    override val extraLarge: Float,
) : ValuePreset<Float>
