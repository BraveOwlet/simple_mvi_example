import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenViewModel

actual fun koinMviControllersModules(): Module = module {
    viewModel { MainScreenViewModel() }
}
