package ru.braveowlet.kmmpr.features.main_screen.di

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelHilt @Inject constructor() : MainScreenViewModel()