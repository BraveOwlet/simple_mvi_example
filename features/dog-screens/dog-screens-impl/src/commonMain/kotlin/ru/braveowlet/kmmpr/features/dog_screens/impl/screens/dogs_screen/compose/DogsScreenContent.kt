package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.dogs_screen_button_get_dog
import kmmpr.core.recources.generated.resources.dogs_screen_button_save_dog
import kmmpr.core.recources.generated.resources.dogs_screen_title
import kmmpr.core.recources.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.dogs_screen.mvi.DogsScreenState

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun DogsScreenContent(
    state: DogsScreenState,
    snackbarHostState: SnackbarHostState,
    onClickButtonBack: () -> Unit,
    onClickButtonGetDog: () -> Unit,
    onClickButtonSaveDog: (Dog) -> Unit,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.dogs_screen_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onClickButtonBack
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = onClickButtonGetDog
                    ) {
                        Text(stringResource(Res.string.dogs_screen_button_get_dog))
                    }
                    TextButton(
                        enabled = state.dog != null,
                        onClick = {
                            state.dog?.let(onClickButtonSaveDog)
                        }
                    ) {
                        Text(stringResource(Res.string.dogs_screen_button_save_dog))
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ){
            if (state.dog != null) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = state.dog.url,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }

    }
}
