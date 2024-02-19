package ru.braveowlet.kmmpr.features.dogs_screen.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import ru.braveowlet.kmmpr.common.mvi.koin.MviScreen
import ru.braveowlet.kmmpr.features.dogs_screen.impl.compose.DogsScreenContent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenAction
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenEffect
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenEvent
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenModel
import ru.braveowlet.kmmpr.features.dogs_screen.impl.mvi.DogsScreenState

internal class DogsScreen :
    MviScreen<DogsScreenAction, DogsScreenEffect, DogsScreenEvent, DogsScreenState>(
        tag = DogsScreenModel.tag
    ) {
    @Composable
    override fun MviContent(
        state: DogsScreenState,
        eventFlow: Flow<DogsScreenEvent>,
        acceptAction: (DogsScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            eventFlow.collect { event ->
                when (event) {
                    is DogsScreenEvent.NavigateToBack -> navigator.pop()
                }
            }
        }

        DogsScreenContent(
            onClickBack = {
                acceptAction(DogsScreenAction.ClickButtonBack)
            }
        )
    }

}