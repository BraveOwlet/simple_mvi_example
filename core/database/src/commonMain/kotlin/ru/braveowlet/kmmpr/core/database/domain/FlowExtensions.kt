package ru.braveowlet.kmmpr.core.database.domain

import app.cash.sqldelight.Query
import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T : Any> Query<T>.asFlowList(): Flow<List<T>> = asFlow().map { it.awaitAsList() }
