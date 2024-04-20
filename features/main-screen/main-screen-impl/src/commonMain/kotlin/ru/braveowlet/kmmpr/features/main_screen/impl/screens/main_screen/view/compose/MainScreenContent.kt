package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.main_screen_button_go_to_dogs_screen
import kmmpr.core.recources.generated.resources.main_screen_button_go_to_resources_screen
import kmmpr.core.recources.generated.resources.main_screen_button_go_to_saved_dogs_screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.intents.MainScreenState

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun MainScreenContent(
    state: MainScreenState,
    onClickButtonDogsScreen: () -> Unit,
    onClickButtonSavedDogsScreen: () -> Unit,
    onClickButtonResourcesScreen: () -> Unit,
) {
    MaterialTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = onClickButtonDogsScreen
                ) {
                    Text(stringResource(Res.string.main_screen_button_go_to_dogs_screen))
                }

                Button(
                    onClick = onClickButtonSavedDogsScreen
                ) {
                    Text(stringResource(Res.string.main_screen_button_go_to_saved_dogs_screen))
                }

                Button(
                    onClick = onClickButtonResourcesScreen
                ) {
                    Text(stringResource(Res.string.main_screen_button_go_to_resources_screen))
                }
            }
        }
    }
}
