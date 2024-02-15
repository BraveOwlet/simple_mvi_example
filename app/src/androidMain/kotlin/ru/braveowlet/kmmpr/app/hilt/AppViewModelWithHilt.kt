package ru.braveowlet.kmmpr.app.hilt

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi
import ru.braveowlet.kmmpr.features.main_screen.di.MainScreenFeature
import javax.inject.Inject

@HiltViewModel
class AppViewModelWithHilt @Inject constructor(
    @MainScreenFeature val mainScreenFeatureApi: MainScreenFeatureApi
) : ViewModel()
