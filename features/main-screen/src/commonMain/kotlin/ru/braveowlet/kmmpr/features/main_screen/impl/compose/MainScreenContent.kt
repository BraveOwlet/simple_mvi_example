package ru.braveowlet.kmmpr.features.main_screen.impl.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.braveowlet.kmmpr.core.recources.MR
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenState
import ru.braveowlet.kmmpr.recources.getImageResource
import ru.braveowlet.kmmpr.recources.getStringResource

@Composable
fun MainScreenContent(
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
                Text(getStringResource(MR.strings.app_name))

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
                        Image(getImageResource(MR.images.baseline), null)
                    }
                }
            }
        }
    }
}
