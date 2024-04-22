package ru.braveowlet.common.logger

actual fun getThreadName() : String = Thread.currentThread().name
