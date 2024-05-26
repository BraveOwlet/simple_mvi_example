package ru.braveowlet.kmmpr.features.main_screen.impl.screens.main_screen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.core.resources.Res
import ru.braveowlet.kmmpr.core.resources.main_screen_button_go_to_dogs_screen
import ru.braveowlet.kmmpr.core.resources.main_screen_button_go_to_saved_dogs_screen
import ru.braveowlet.kmmpr.core.resources.main_screen_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreenContent(
    onClickButtonDogsScreen: () -> Unit,
    onClickButtonSavedDogsScreen: () -> Unit,
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(Res.string.main_screen_title))
                    },
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 20.dp)
                            .padding(vertical = 20.dp),
                        onClick = onClickButtonDogsScreen
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(stringResource(Res.string.main_screen_button_go_to_dogs_screen))
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 20.dp),
                        onClick = onClickButtonSavedDogsScreen
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(stringResource(Res.string.main_screen_button_go_to_saved_dogs_screen))
                        }
                    }
                }
            }
        }
    }
}
