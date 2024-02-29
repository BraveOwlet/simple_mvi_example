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
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.back
import kmmpr.core.recources.generated.resources.dogs_screen_button_get_new_random_dog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
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
                Text(stringResource(Res.string.back))
            }
            Button(
                onClick = onClickButtonGetImageRandomDog
            ) {
                Text(stringResource(Res.string.dogs_screen_button_get_new_random_dog))
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
