package ru.braveowlet.kmmpr.core.database.domain.dogs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogEntity(
    @PrimaryKey(autoGenerate = true)  val id: Long,
    val url: String,
)
