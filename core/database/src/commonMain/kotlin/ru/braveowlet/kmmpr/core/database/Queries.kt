package ru.braveowlet.kmmpr.core.database

data class Queries(
    val shared: SharedEntityQueries,
    val dogs: DogEntityQueries,
)
