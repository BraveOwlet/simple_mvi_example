package ru.braveowlet.kmmpr.features.dogs_screen.impl.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import ru.braveowlet.kmmpr.core.recources.MR
import ru.braveowlet.kmmpr.recources.getStringResource

@Composable
internal fun DogsScreenContent(
    urlImageDog: String,
    snackbarHostState: SnackbarHostState,
    onClickButtonBack: () -> Unit,
    onClickButtonGetImageRandomDog: () -> Unit,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column {
            Button(
                onClick = onClickButtonBack
            ) {
                Text(getStringResource(MR.strings.back))
            }
            Button(
                onClick = onClickButtonGetImageRandomDog
            ) {
                Text(getStringResource(MR.strings.dogs_screen_button_get_new_random_dog))
            }
            Text(urlImageDog)
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = urlImageDog,
                contentDescription = null,
            )
        }
    }
}
