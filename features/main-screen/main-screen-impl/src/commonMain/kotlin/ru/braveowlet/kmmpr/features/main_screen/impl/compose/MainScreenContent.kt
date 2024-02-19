package ru.braveowlet.kmmpr.features.main_screen.impl.compose

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
import ru.braveowlet.kmmpr.core.recources.MR
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenState
import ru.braveowlet.kmmpr.recources.getStringResource

@Composable
internal fun MainScreenContent(
    state: MainScreenState,
    onClickButtonDogsScreen: () -> Unit,
    onClickButtonSavedDogsScreen: () -> Unit,
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
                    Text(getStringResource(MR.strings.main_screen_button_go_to_dogs_screen))
                }

                Button(
                    onClick = onClickButtonSavedDogsScreen
                ) {
                    Text(getStringResource(MR.strings.main_screen_button_go_to_saved_dogs_screen))
                }
            }
        }
    }
}
