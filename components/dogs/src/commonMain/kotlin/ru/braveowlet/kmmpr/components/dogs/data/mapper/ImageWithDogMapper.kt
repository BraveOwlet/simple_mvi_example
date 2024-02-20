package ru.braveowlet.kmmpr.components.dogs.data.mapper

import ru.braveowlet.kmmpr.components.dogs.data.dto.ImageWithDogDto
import ru.braveowlet.kmmpr.components.dogs.domain.model.ImageWithDog

internal fun ImageWithDogDto.toDomain(): ImageWithDog = ImageWithDog(
    url = this.message
)
