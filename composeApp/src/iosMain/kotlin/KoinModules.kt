import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.mvi.MainScreenViewModel

fun initKoin(){
    startKoin {
        modules(koinMviControllersModules())
    }
}

actual fun koinMviControllersModules(): Module = module {
    single<MainScreenViewModel> { MainScreenViewModel() }
}
