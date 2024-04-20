package ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmmpr.core.recources.generated.resources.Res
import kmmpr.core.recources.generated.resources.app_name
import kmmpr.core.recources.generated.resources.back
import kmmpr.core.recources.generated.resources.baseline
import kmmpr.core.recources.generated.resources.compose
import kmmpr.core.recources.generated.resources.multiplatform
import kmmpr.core.recources.generated.resources.photo
import kmmpr.core.recources.generated.resources.resources_screen_drawable
import kmmpr.core.recources.generated.resources.resources_screen_strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.intents.ResourcesScreenState

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ResourcesScreenContent(
    state: ResourcesScreenState,
    onClickButtonBack: () -> Unit,
) {
    MaterialTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
            ) {
                Button(
                    onClick = onClickButtonBack
                ) {
                    Text(stringResource(Res.string.back))
                }
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(Res.string.resources_screen_strings)
                )
                HorizontalDivider()
                Text(
                    text = stringResource(Res.string.app_name)
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(Res.string.resources_screen_drawable)
                )
                HorizontalDivider()
                Image(
                    painter = painterResource(Res.drawable.multiplatform),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(Res.drawable.baseline),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(Res.drawable.compose),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(Res.drawable.photo),
                    contentDescription = null
                )
            }
        }
    }
}
