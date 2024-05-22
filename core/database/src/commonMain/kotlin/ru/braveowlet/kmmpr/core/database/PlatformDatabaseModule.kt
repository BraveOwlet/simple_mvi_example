package ru.braveowlet.kmmpr.core.database

import org.koin.core.module.Module

internal expect fun platformDatabaseModule(
    fileName: String,
): Module
