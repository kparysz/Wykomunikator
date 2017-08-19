package pl.kparysz.wykomessages.di.modules

import dagger.Module
import dagger.Provides
import pl.kparysz.wykomessages.chat.repository.ChatDetailApi
import pl.kparysz.wykomessages.chat.repository.ChatDetailRepository
import pl.kparysz.wykomessages.login.repository.UserLoginApi
import pl.kparysz.wykomessages.login.repository.UserRepository
import pl.kparysz.wykomessages.messages.repository.ConversationListApi
import pl.kparysz.wykomessages.messages.repository.ConversationListRepository
import pl.kparysz.wykomessages.network.WykopHttpClient
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.utils.SecretInfoApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesUserRepository(retrofit: Retrofit.Builder,
                               wykopPreferencesApi: WykopPreferencesApi,
                               secretInfoApi: SecretInfoApi,
                               client: WykopHttpClient): UserLoginApi {
        return UserRepository(retrofit, wykopPreferencesApi, secretInfoApi, client)
    }

    @Provides
    @Singleton
    fun providesConversationListRepository(retrofit: Retrofit.Builder,
                                           wykopPreferencesApi: WykopPreferencesApi,
                                           secretInfoApi: SecretInfoApi,
                                           client: WykopHttpClient): ConversationListApi {
        return ConversationListRepository(retrofit, wykopPreferencesApi, secretInfoApi, client)
    }

    @Provides
    @Singleton
    fun providesChatDetailRepository(retrofit: Retrofit.Builder,
                                     wykopPreferencesApi: WykopPreferencesApi,
                                     secretInfoApi: SecretInfoApi,
                                     client: WykopHttpClient): ChatDetailApi {
        return ChatDetailRepository(retrofit, wykopPreferencesApi, secretInfoApi, client)
    }
}