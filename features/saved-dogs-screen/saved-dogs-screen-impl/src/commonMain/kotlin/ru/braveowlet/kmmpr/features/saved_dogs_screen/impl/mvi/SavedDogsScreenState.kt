package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.mvi

import ru.braveowlet.kmmpr.common.mvi.general.MviState

internal data class SavedDogsScreenState(
    val data: String,
): MviState