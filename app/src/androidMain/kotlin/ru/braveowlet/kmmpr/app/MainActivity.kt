package ru.braveowlet.kmmpr.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.koinInject
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainScreenFeatureApi = koinInject<MainScreenApi>()
            MaterialTheme {
                Navigator(
                    mainScreenFeatureApi.mainScreen()
                )
            }
        }
    }
}
