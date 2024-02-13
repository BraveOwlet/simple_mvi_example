package ru.braveowlet.kmmpr.features.main_screen.compose

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FirstScreenContent(
    state: MainScreenState,
    snackbarHostState: SnackbarHostState,
    onClickButton: () -> Unit,
) {
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
                    onClick = onClickButton
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
