package ru.braveowlet.kmmpr.features.saved_dogs_screen.impl.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.braveowlet.kmmpr.core.recources.MR
import ru.braveowlet.kmmpr.recources.getStringResource

@Composable
internal fun SavedDogsScreenContent(
    onClickBack: () -> Unit
) {
    Scaffold {
        Column {
            Button(
                onClick = onClickBack
            ) {
                Text(getStringResource(MR.strings.back))
            }
            Text("SavedDogsScreenContent")
        }
    }
}
