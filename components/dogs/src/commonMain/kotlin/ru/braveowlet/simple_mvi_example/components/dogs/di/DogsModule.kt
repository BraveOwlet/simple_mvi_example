package ru.braveowlet.simple_mvi_example.components.dogs.di

import org.koin.dsl.module
import ru.braveowlet.simple_mvi_example.components.dogs.data.api.DogsApi
import ru.braveowlet.simple_mvi_example.components.dogs.data.api.DogsApiImpl
import ru.braveowlet.simple_mvi_example.components.dogs.data.repository.DogsRepositoryImpl
import ru.braveowlet.simple_mvi_example.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.GetRandomDogUseCase
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.GetRandomDogUseCaseImpl
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.ObserveRandomDogUseCase
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.ObserveRandomDogUseCaseImpl
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.SaveDogUseCase
import ru.braveowlet.simple_mvi_example.components.dogs.domain.usecase.SaveDogUseCaseImpl

val dogsModule
    get() = module {
        single<DogsApi> { DogsApiImpl(get()) }
        single<DogsRepository> { DogsRepositoryImpl(get(), get()) }
        single<GetRandomDogUseCase> { GetRandomDogUseCaseImpl(get()) }
        single<SaveDogUseCase> { SaveDogUseCaseImpl(get()) }
        single<ObserveRandomDogUseCase> { ObserveRandomDogUseCaseImpl(get()) }
    }
