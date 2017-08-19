package pl.kparysz.wykomessages.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.prefs.WykopPreferences
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.rx.SubscriptionApi
import pl.kparysz.wykomessages.rx.SubscriptionManager
import pl.kparysz.wykomessages.utils.SecretInfo
import pl.kparysz.wykomessages.utils.SecretInfoApi
import unofficial.coderoid.wykop.newapp.utils.Navigator
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi
import javax.inject.Singleton

@Module
open class AppModule(internal var application: App) {

    @Provides
    fun providesApplication(): Application {
        return application
    }

    @Provides
    fun providesContext(): Context {
        return this.application
    }

    @Provides
    fun providesNavigator(): NavigatorApi {
        return Navigator(application)
    }

    @Provides
    @Singleton
    fun providesWykopPreferences(): WykopPreferencesApi {
        return WykopPreferences(application)
    }

    @Provides
    fun providesSubscriptionManager(): SubscriptionApi {
        return SubscriptionManager(AndroidSchedulers.mainThread(), Schedulers.io())
    }

    @Provides
    @Singleton
    open fun providesSecretInfoApi(): SecretInfoApi {
        return SecretInfo()
    }

}
