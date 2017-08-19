package pl.kparysz.wykomessages.di

import dagger.Component
import pl.kparysz.wykomessages.chat.view.ChatActivity
import pl.kparysz.wykomessages.di.modules.AppModule
import pl.kparysz.wykomessages.di.modules.NetworkModule
import pl.kparysz.wykomessages.di.modules.PresentersModule
import pl.kparysz.wykomessages.di.modules.RepositoryModule
import pl.kparysz.wykomessages.login.view.LoginWebActivity
import pl.kparysz.wykomessages.messages.view.MessagesActivity
import pl.kparysz.wykomessages.splash.view.SplashActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, PresentersModule::class, RepositoryModule::class))
interface Injector {
    fun inject(activity: SplashActivity)
    fun inject(activity: LoginWebActivity)
    fun inject(activity: MessagesActivity)
    fun inject(activity: ChatActivity)
}
