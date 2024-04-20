package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.back
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SavedDogsScreenContent(
    dogs: List<Dog>,
    onClickBack: () -> Unit
) {
    Scaffold {
        Column {
            Button(
                onClick = onClickBack
            ) {
                Text(stringResource(Res.string.back))
            }
            Text("SavedDogsScreenContent")
            HorizontalDivider()
            LazyColumn {
                items(
                    items = dogs,
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
