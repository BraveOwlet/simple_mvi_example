package ru.braveowlet.kmmpr.components.dogs.data.mapper

import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.core.database.table.dogs.DogEntity

internal fun DogDto.toDomain(): Dog = Dog(
    url = this.message
)

internal fun DogEntity.toDomain(): Dog = Dog(
    url = url
)

internal fun Dog.toEntity(): DogEntity = DogEntity(
    url = url,
)
