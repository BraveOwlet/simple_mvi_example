import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.first_screen.mvi.ScreenViewModel

actual fun koinMviControllersModules(): Module = module {
    viewModel { ScreenViewModel() }
}
