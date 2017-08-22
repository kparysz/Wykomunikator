package wykomessages.di

import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.di.Injector
import pl.kparysz.wykomessages.di.modules.PresentersModule
import pl.kparysz.wykomessages.di.modules.RepositoryModule
import wykomessages.di.modules.AutomatedAppModule
import wykomessages.di.modules.AutomatedNetworkModule

class AutomatedApp : App() {

    companion object {
        const val WYKOP_API_URL = "http://localhost:12345/"
        @JvmStatic lateinit var injector: AutomatedInjector
    }

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAutomatedInjector
                .builder()
                .presentersModule(PresentersModule())
                .networkModule(AutomatedNetworkModule(WYKOP_API_URL))
                .repositoryModule(RepositoryModule())
                .appModule(AutomatedAppModule(this))
                .build()
    }

    override fun getInjector(): Injector = injector
}
