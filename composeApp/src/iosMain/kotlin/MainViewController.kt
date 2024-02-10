import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.coroutines.flow.StateFlow

fun MainViewController() = ComposeUIViewController { App() }
