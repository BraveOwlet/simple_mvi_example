package ru.braveowlet.kmmpr.components.dogs.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogEntity(
    @PrimaryKey val url: String,
)
