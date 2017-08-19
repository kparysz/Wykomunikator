package pl.kparysz.wykomessages.login.repository

import io.reactivex.Observable
import okhttp3.OkHttpClient
import pl.kparysz.wykomessages.models.dataclass.User
import pl.kparysz.wykomessages.network.WykopHttpClient
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.utils.SecretInfoApi
import retrofit2.Retrofit

class UserRepository(val retrofit: Retrofit.Builder,
                     val wykopPreferencesApi: WykopPreferencesApi,
                     val secretInfoApi: SecretInfoApi,
                     val client: WykopHttpClient) : UserLoginApi {

    override fun loginUser(token: String): Observable<User> =
            restAdapter(client.getClientWithPostParameter(token))
                    .loginUser(token, secretInfoApi.getAppKey())
                    .map { it ->
                        wykopPreferencesApi.saveUserKey(it.userkey.orEmpty())
                        User(it.name.orEmpty())
                    }

    private fun restAdapter(client: OkHttpClient): LoginUserRetrofit {
        return retrofit.client(client).build().create(LoginUserRetrofit::class.java)
    }
}