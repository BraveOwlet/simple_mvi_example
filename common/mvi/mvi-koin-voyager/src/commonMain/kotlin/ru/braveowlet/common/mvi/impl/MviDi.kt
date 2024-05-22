package ru.braveowlet.common.mvi.impl

import org.koin.core.module.Module
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

inline fun <reified T : MviView<*, *, *>> Module.createFactoryModel(
    crossinline modelFactory: Scope.(tag: String) -> MviModel<*, *, *, *>,
) {
    val tag = T::class.simpleName ?: throw MviViewNoSimpleNameThrowable()
    factory<MviModel<*, *, *, *>>(named(tag)) {
        modelFactory(tag)
    }
}

inline fun <reified T : MviView<*, *, *>> Module.createFactoryModel(
    crossinline modelFactory: Scope.(tag: String, params: ParametersHolder) -> MviModel<*, *, *, *>,
) {
    val tag = T::class.simpleName ?: throw MviViewNoSimpleNameThrowable()
    factory<MviModel<*, *, *, *>>(named(tag)) { params ->
        modelFactory(tag, params)
    }
}

class MviViewNoSimpleNameThrowable : Throwable(
    message = "MviView<*,*,*,*> implementation has no simpleName"
)
