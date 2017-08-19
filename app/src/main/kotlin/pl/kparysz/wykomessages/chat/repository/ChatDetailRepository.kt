package pl.kparysz.wykomessages.chat.repository

import io.reactivex.Observable
import okhttp3.OkHttpClient
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail
import pl.kparysz.wykomessages.models.pojo.PrivateMessage
import pl.kparysz.wykomessages.network.WykopHttpClient
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.utils.SecretInfoApi
import retrofit2.Retrofit

class ChatDetailRepository(val retrofit: Retrofit.Builder,
                           val wykopPreferencesApi: WykopPreferencesApi,
                           val secretInfoApi: SecretInfoApi,
                           val wykopClient: WykopHttpClient) : ChatDetailApi {

    override fun getChatDetail(userName: String): Observable<List<PrivateMessageDetail>> =
            restAdapter(wykopClient.client)
                    .getChatDetail(userName, secretInfoApi.getAppKey(), wykopPreferencesApi.getUserKey())
                    .map { it.map { it -> convertToPrivateMessageDetail(it) } }

    private fun convertToPrivateMessageDetail(privateMessage: PrivateMessage) = PrivateMessageDetail(
            privateMessage.body.orEmpty(),
            privateMessage.author.orEmpty(),
            privateMessage.lastUpdate.orEmpty(),
            privateMessage.direction.orEmpty() == "sended")

    private fun restAdapter(client: OkHttpClient): ChatDetailRetrofit {
        return retrofit.client(client).build().create(ChatDetailRetrofit::class.java)
    }
}