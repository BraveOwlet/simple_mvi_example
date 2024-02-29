package ru.braveowlet.kmmpr.components.dogs.data.mapper

import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal fun dogEntityToDomain(
    id: Long,
    url: String
): Dog = Dog(
    url = url
)
