import androidx.compose.runtime.Composable
import org.koin.compose.koinInject
import ru.braveowlet.kmmpr.features.main_screen.MainScreenFeatureApi

@Composable
fun App() {
    val mainScreenFeatureApi = koinInject<MainScreenFeatureApi>()
    mainScreenFeatureApi.mainScreen()
}