package pl.kparysz.wykomessages.di.modules

import dagger.Module
import dagger.Provides
import pl.kparysz.wykomessages.chat.presenter.ChatPresenter
import pl.kparysz.wykomessages.chat.repository.ChatDetailApi
import pl.kparysz.wykomessages.login.presenter.LoginPresenter
import pl.kparysz.wykomessages.login.repository.UserLoginApi
import pl.kparysz.wykomessages.messages.presenter.MainPresenter
import pl.kparysz.wykomessages.messages.repository.ConversationListApi
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.rx.SubscriptionApi
import pl.kparysz.wykomessages.settings.SettingsPresenter
import pl.kparysz.wykomessages.splash.presenter.SplashPresenter
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi

@Module
class PresentersModule {
    @Provides
    fun providesSplashPresenter(wykopPreferencesApi: WykopPreferencesApi): SplashPresenter {
        return SplashPresenter(wykopPreferencesApi)
    }

    @Provides
    fun providesLoginPresenter(wykopPreferencesApi: WykopPreferencesApi,
                               subscriptionManager: SubscriptionApi,
                               userLoginApi: UserLoginApi): LoginPresenter {
        return LoginPresenter(wykopPreferencesApi,
                subscriptionManager,
                userLoginApi)
    }

    @Provides
    fun providesMessagesPresenter(subscriptionManager: SubscriptionApi,
                                  navigatorApi: NavigatorApi,
                                  conversationListApi: ConversationListApi): MainPresenter {
        return MainPresenter(subscriptionManager,
                navigatorApi,
                conversationListApi)
    }

    @Provides
    fun providesChatPresenter(subscriptionManager: SubscriptionApi,
                              wykopPreferencesApi: WykopPreferencesApi,
                              chatDetailApi: ChatDetailApi): ChatPresenter {
        return ChatPresenter(subscriptionManager,
                wykopPreferencesApi,
                chatDetailApi)
    }

    @Provides
    fun providesSettingsPresenter(wykopPreferencesApi: WykopPreferencesApi): SettingsPresenter {
        return SettingsPresenter(wykopPreferencesApi)
    }
}
