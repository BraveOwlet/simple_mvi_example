package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.view.compose

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
import kmmpr.core.recources.generated.resources.dogs_screen_button_get_dog
import kmmpr.core.recources.generated.resources.dogs_screen_button_save_dog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.model.state.DogsScreenState

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun DogsScreenContent(
    state: DogsScreenState,
    snackbarHostState: SnackbarHostState,
    onClickButtonBack: () -> Unit,
    onClickButtonGetDog: () -> Unit,
    onClickButtonSaveDog: (Dog) -> Unit,
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
                onClick = onClickButtonGetDog
            ) {
                Text(stringResource(Res.string.dogs_screen_button_get_dog))
            }
            if (state.dog!=null) {
                Text(state.dog.url)
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = state.dog.url,
                    contentDescription = null,
                )
                Button(
                    onClick = {
                        onClickButtonSaveDog(state.dog)
                    }
                ) {
                    Text(stringResource(Res.string.dogs_screen_button_save_dog))
                }
            }
        }
    }
}
