package ru.braveowlet.simple_mvi_example.components.dogs.data.mapper

import ru.braveowlet.simple_mvi_example.components.dogs.data.dto.DogDto
import ru.braveowlet.simple_mvi_example.components.dogs.domain.model.Dog
import ru.braveowlet.simple_mvi_example.core.database.table.dogs.DogEntity

internal fun DogDto.toDomain(): Dog = Dog(
    url = this.message
)

internal fun DogEntity.toDomain(): Dog = Dog(
    url = url
)

internal fun Dog.toEntity(): DogEntity = DogEntity(
    url = url,
)
