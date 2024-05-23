package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.core.resources.Res
import ru.braveowlet.kmmpr.core.resources.ic_arrow_back
import ru.braveowlet.kmmpr.core.resources.saved_dogs_screen_title
import ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi.SavedDogsScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SavedDogsScreenContent(
    state: SavedDogsScreenState,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.saved_dogs_screen_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = state.dogs,
                    key = { it.url }
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = it.url,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}
