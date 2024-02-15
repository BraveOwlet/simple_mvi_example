import androidx.compose.runtime.Composable
import org.koin.compose.koinInject
import ru.braveowlet.kmmpr.features.main_screen.api.MainScreenFeatureApi

@Composable
fun AppWithKoin() {
    val mainScreenFeatureApi = koinInject<MainScreenFeatureApi>()
    mainScreenFeatureApi.mainScreen()
}