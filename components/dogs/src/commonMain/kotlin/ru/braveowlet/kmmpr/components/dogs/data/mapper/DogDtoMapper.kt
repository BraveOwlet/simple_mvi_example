package ru.braveowlet.kmmpr.components.dogs.data.mapper

import ru.braveowlet.kmmpr.components.dogs.data.dto.DogDto
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal fun DogDto.toDomain(): Dog = Dog(
    url = this.message
)
