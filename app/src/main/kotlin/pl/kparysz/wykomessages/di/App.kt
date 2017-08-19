package pl.kparysz.wykomessages.di

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import pl.kparysz.wykomessages.di.modules.AppModule
import pl.kparysz.wykomessages.di.modules.NetworkModule
import pl.kparysz.wykomessages.di.modules.PresentersModule
import pl.kparysz.wykomessages.di.modules.RepositoryModule

open class App : Application() {

    companion object {
        @JvmStatic lateinit var uiInject: Injector
    }

    override fun onCreate() {
        super.onCreate()
        uiInject = DaggerInjector
                .builder()
                .presentersModule(PresentersModule())
                .networkModule(NetworkModule("http://a.wykop.pl"))
                .repositoryModule(RepositoryModule())
                .appModule(AppModule(this))
                .build()
    }

    open fun getInjector() = uiInject

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        multiDexInstall()
    }

    fun multiDexInstall() {
        MultiDex.install(this)
    }
}

