package ru.braveowlet.kmmpr.core.database.data.dogs

import ru.braveowlet.kmmpr.core.database.DogTable
import ru.braveowlet.kmmpr.core.database.domain.dogs.DogEntity

internal fun DogTable.toEntity() = DogEntity(
    id = this.id,
    url = this.url,
)

internal fun DogEntity.toTable() = DogTable(
    id = this.id,
    url = this.url,
)
