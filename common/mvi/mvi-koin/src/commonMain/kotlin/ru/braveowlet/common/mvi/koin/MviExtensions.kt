package ru.braveowlet.common.mvi.koin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.common.mvi.general.MviEvent

@Composable
inline fun <reified T : MviEvent> Flow<T>.collectEvent(
    crossinline onEvent: suspend CoroutineScope.(T)->Unit
){
    LaunchedEffect(Unit){
        this@collectEvent.collect{
            onEvent(it)
        }
    }
}
