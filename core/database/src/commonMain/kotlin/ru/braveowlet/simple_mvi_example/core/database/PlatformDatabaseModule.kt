package ru.braveowlet.simple_mvi_example.core.database

import org.koin.core.module.Module

internal expect fun platformDatabaseModule(fileName: String): Module
