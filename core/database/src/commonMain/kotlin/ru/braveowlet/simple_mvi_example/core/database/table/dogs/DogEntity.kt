package ru.braveowlet.simple_mvi_example.core.database.table.dogs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogEntity(
    @PrimaryKey val url: String,
)
