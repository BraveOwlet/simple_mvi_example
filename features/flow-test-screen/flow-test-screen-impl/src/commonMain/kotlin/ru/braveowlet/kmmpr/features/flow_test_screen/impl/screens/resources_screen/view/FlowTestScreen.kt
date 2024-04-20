package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.view

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.braveowlet.common.mvi.general.presentation.collectEvent
import ru.braveowlet.common.mvi.impl.MviScreen
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents.FlowTestScreenAction
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents.FlowTestScreenEffect
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.intents.FlowTestScreenEvent
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state.FlowTestScreenState
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.view.compose.FlowTestScreenContent

internal class FlowTestScreen(
    tag: String
) : MviScreen<FlowTestScreenAction, FlowTestScreenEffect, FlowTestScreenEvent, FlowTestScreenState>(
    tag = tag,
    content = { state ->
        val navigator = LocalNavigator.currentOrThrow

        val stateListValue1: LazyListState = rememberLazyListState()
        val stateListValue2: LazyListState = rememberLazyListState()
        val stateListResult: LazyListState = rememberLazyListState()

        collectEvent { event ->
            when (event) {
                FlowTestScreenEvent.NavigateToBack -> navigator.pop()
            }
        }

        LaunchedEffect(state.value1) {
            state.value1.lastIndex.takeIf { it > 1 }?.let {
                stateListValue1.scrollToItem(it)
            }
        }

        LaunchedEffect(state.value2) {
            state.value2.lastIndex.takeIf { it > 1 }?.let {
                stateListValue2.scrollToItem(it)
            }
        }

        LaunchedEffect(state.resultValue) {
            state.resultValue.lastIndex.takeIf { it > 1 }?.let {
                stateListResult.scrollToItem(it)
            }
        }

        FlowTestScreenContent(
            state = state,
            stateListValue1 = stateListValue1,
            stateListValue2 = stateListValue2,
            stateListResult = stateListResult,
            onClickButtonBack = { emit(FlowTestScreenAction.ClickButtonBack) },
            onClickButtonTest = { emit(FlowTestScreenAction.ClickButtonTest(it)) },
            onClickButtonClean = { emit(FlowTestScreenAction.ClickButtonClean) },
        )
    }
)
