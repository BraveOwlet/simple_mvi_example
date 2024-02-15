import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.di.mainScreenModule

fun initKoin() {
    startKoin {
        modules(appModules())
    }
}

private fun iosModules() = module {  }

actual fun appModules(): List<Module> = iosModules() + mainScreenModule()
