import org.koin.core.module.Module
import org.koin.dsl.module
import ru.braveowlet.kmmpr.features.main_screen.di.mainScreenModule

private fun androidModule() = module {}

actual fun appModules(): List<Module> = androidModule() + mainScreenModule()
