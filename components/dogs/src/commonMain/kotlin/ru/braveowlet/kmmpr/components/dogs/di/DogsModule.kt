package ru.braveowlet.kmmpr.components.dogs.di

import org.koin.dsl.module
import ru.braveowlet.kmmpr.components.dogs.data.repository.DogsRepositoryImpl
import ru.braveowlet.kmmpr.components.dogs.domain.repository.DogsRepository
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.GetRandomImageWithDogsUseCase
import ru.braveowlet.kmmpr.components.dogs.domain.usecase.GetRandomImageWithDogsUseCaseImpl

val dogsModule = module {
    single<DogsRepository> { DogsRepositoryImpl(get()) }
    single<GetRandomImageWithDogsUseCase> { GetRandomImageWithDogsUseCaseImpl(get()) }
}
