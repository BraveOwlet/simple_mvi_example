package ru.braveowlet.kmmpr.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.braveowlet.kmmpr.app.compose.AppContentWithKoin
import ru.braveowlet.kmmpr.app.hilt.AppContentWithHilt
import ru.braveowlet.kmmpr.common.mvi.di.DiProvider
import ru.braveowlet.kmmpr.common.mvi.di.DiType

private val CURRENT_DI_TYPE = DiType.KOIN

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiProvider(CURRENT_DI_TYPE) {
                when (CURRENT_DI_TYPE) {
                    DiType.KOIN -> AppContentWithKoin()
                    DiType.HILT -> AppContentWithHilt()
                }
            }
        }
    }
}
