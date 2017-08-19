package wykomessages.di

import dagger.Component
import pl.kparysz.wykomessages.di.Injector
import pl.kparysz.wykomessages.di.modules.AppModule
import pl.kparysz.wykomessages.di.modules.NetworkModule
import pl.kparysz.wykomessages.di.modules.PresentersModule
import pl.kparysz.wykomessages.di.modules.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, PresentersModule::class, RepositoryModule::class))
interface AutomatedInjector : Injector {}