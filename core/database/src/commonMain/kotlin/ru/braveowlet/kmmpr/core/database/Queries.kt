package ru.braveowlet.kmmpr.core.database

data class Queries(
    val shared: SharedTableQueries,
    val dogs: DogsTableQueries,
)
