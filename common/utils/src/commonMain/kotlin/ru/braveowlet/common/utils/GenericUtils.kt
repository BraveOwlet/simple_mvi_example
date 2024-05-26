package ru.braveowlet.common.utils

import kotlin.reflect.KClass

val <T : KClass<*>> T.simpleNameOrThrow: String
    get() = simpleName ?: throw Throwable(
        message = "Implementation class does not have simpleName"
    )
