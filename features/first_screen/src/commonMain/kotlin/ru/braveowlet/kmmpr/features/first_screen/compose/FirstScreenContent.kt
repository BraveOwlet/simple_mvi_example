package ru.braveowlet.kmmpr.features.first_screen.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenAction
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenController
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenEvent

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FirstScreenContent(
    controller: ScreenController
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val state by controller.getState(scope).collectAsState()

    LaunchedEffect(Unit) {
        controller.eventFlow().collect {
            when (it) {
                is ScreenEvent.ShowMessage -> snackbarHostState.showSnackbar(it.message)
            }
        }
    }

    fun acceptAction(action: ScreenAction){
        scope.launch {
            controller.acceptAction(action)
        }
    }

    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(state.data)

                Button(
                    onClick = { acceptAction(ScreenAction.ClickButton) }
                ) {
                    Text("Click")
                }

                AnimatedVisibility(state.logoIsShowed) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource("compose-multiplatform.xml"), null)
                    }
                }

            }
        }
    }
}