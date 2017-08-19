package pl.kparysz.wykomessages.messages.repository

import io.reactivex.Observable
import okhttp3.OkHttpClient
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail
import pl.kparysz.wykomessages.models.pojo.Conversation
import pl.kparysz.wykomessages.network.WykopHttpClient
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.utils.SecretInfoApi
import retrofit2.Retrofit

class ConversationListRepository(val retrofit: Retrofit.Builder,
                                 val wykopPreferencesApi: WykopPreferencesApi,
                                 val secretInfoApi: SecretInfoApi,
                                 val wykopClient: WykopHttpClient) : ConversationListApi {
    override fun getAllMessages(): Observable<List<ConversationDetail>> {
        return restAdapter(wykopClient.client)
                .getAllMessages(secretInfoApi.getAppKey(), wykopPreferencesApi.getUserKey())
                .map { it -> it.map { convertToConversationDetail(it) } }
    }

    private fun convertToConversationDetail(conversation: Conversation) = ConversationDetail(
            conversation.author.orEmpty(),
            conversation.authorAvatar.orEmpty(),
            conversation.lastUpdate.toString(),
            conversation.status.orEmpty(),
            conversation.authorGroup ?: 0,
            conversation.authorSex.orEmpty()).apply { onClickAction = { } }

    private fun restAdapter(client: OkHttpClient): ConversationListRetrofit {
        return retrofit.client(client).build().create(ConversationListRetrofit::class.java)
    }

}
