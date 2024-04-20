package ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.back
import kmmpr.core.recources.generated.resources.flow_test_screen_run_test
import kmmpr.core.recources.generated.resources.flow_test_screen_stop_test
import kmmpr.core.recources.generated.resources.flow_test_screen_clean
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state.FlowTestResultState
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state.FlowTestScreenState
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.model.state.FlowTestValueState

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun FlowTestScreenContent(
    state: FlowTestScreenState,
    stateListValue1: LazyListState,
    stateListValue2: LazyListState,
    stateListResult: LazyListState,
    onClickButtonBack: () -> Unit,
    onClickButtonTest: (Boolean) -> Unit,
    onClickButtonClean: () -> Unit,
) {
    MaterialTheme {
        Scaffold { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
            ) {
                Button(
                    onClick = onClickButtonBack
                ) {
                    Text(stringResource(Res.string.back))
                }
                Row {
                    ChangeFlowTestState(state.isRun, onClickButtonTest)
                    if (state.resultValue.isNotEmpty()) {
                        CleanButton(onClickButtonClean)
                    }
                }
                FlowTestResultList(state.resultValue, stateListResult)
                FlowTestValueLists(state.value1, state.value2, stateListValue1, stateListValue2)
            }
        }
    }
}

@Composable
private fun ColumnScope.FlowTestValueLists(
    items1: List<FlowTestValueState>,
    items2: List<FlowTestValueState>,
    state1: LazyListState,
    state2: LazyListState,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FlowTestValueList(items1, state1)
        FlowTestValueList(items2, state2)
    }
}

@Composable
private fun RowScope.FlowTestValueList(
    items: List<FlowTestValueState>,
    state: LazyListState,
) {
    LazyColumn(
        state = state,
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .border(1.dp, Color.Black)
    ) {
        items(
            items = items
        ) { value ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = if (value.isRight) Color.Transparent else Color.Red
                    )
            ){
                Text(
                    text = value.toString(),
                    fontSize = 8.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
private fun ColumnScope.FlowTestResultList(
    items: List<FlowTestResultState>,
    state: LazyListState,
) {
    LazyRow(
        state = state,
        modifier = Modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(2.dp)
            )
    ) {
        items(
            items = items
        ) { value ->
            val color = if (value.isRight) Color.Transparent else Color.Red
            Column(
                modifier = Modifier
                    .widthIn(min = 32.dp)
                    .padding(2.dp)
                    .background(
                        color = color,
                        shape = RoundedCornerShape(2.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(value.value1)
                Text(value.value2)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CleanButton(
    onClickButtonClean: () -> Unit,
) {
    Button(
        onClick = onClickButtonClean
    ) {
        Text(stringResource(Res.string.flow_test_screen_clean))
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ChangeFlowTestState(
    isRun: Boolean,
    onClickButtonTest: (Boolean) -> Unit,
) {
    Button(
        onClick = {
            onClickButtonTest(!isRun)
        }
    ) {
        Text(
            stringResource(
                if (isRun) {
                    Res.string.flow_test_screen_stop_test
                } else {
                    Res.string.flow_test_screen_run_test
                }
            )
        )
    }
}
