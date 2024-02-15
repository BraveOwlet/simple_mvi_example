package ru.braveowlet.kmmpr.features.main_screen.di

import dagger.Module as HiltModule
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module as KoinModule
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi
import ru.braveowlet.kmmpr.features.main_screen.impl.MainScreenFeatureImpl
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenViewModel
import javax.inject.Qualifier
import javax.inject.Singleton

actual fun mainScreenModule() : KoinModule = module {
    single<MainScreenFeatureApi> { MainScreenFeatureImpl() }
    viewModel { MainScreenViewModel() }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainScreenFeature

@HiltModule
@InstallIn(SingletonComponent::class)
object MainScreenModule {

    @Provides
    @Singleton
    @MainScreenFeature
    fun provideMainScreenFeature(): MainScreenFeatureApi = MainScreenFeatureImpl()
}