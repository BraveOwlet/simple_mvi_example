package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.back
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SavedDogsScreenContent(
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
        }
    }
}
