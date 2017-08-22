package pl.kparysz.wykomessages.network

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import pl.kparysz.wykomessages.utils.SecretInfoApi


class WykopHttpClient(val secretInfoApi: SecretInfoApi) {

    val client: OkHttpClient
        get() {
            val client = OkHttpClient.Builder()
            client.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .header(APISIGN, RequestFactory.hash(secretInfoApi.getSecretAppKey(),
                                original.url().toString()))
                        .header(ACCEPT, APP_TYPE)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            client.addInterceptor(logging)
            return client.build()
        }

    fun getClientWithPostParameter(parameter: String): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val requestBuilder = createRequest(chain, parameter)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return client.build()
    }

    fun getClientWitBodyParameter(parameter: String): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val requestBuilder = createRequest(chain, parameter)
            val formBody = FormBody.Builder()
                    .add(BODY, parameter)
                    .build()
            requestBuilder.post(formBody)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return client.build()
    }

    private fun createRequest(chain: Interceptor.Chain, parameter: String): Request.Builder {
        val original = chain.request()
        return original.newBuilder()
                .header(APISIGN, RequestFactory.hash(secretInfoApi.getSecretAppKey(),
                        original.url().toString() + parameter))
                .header(ACCEPT, APP_TYPE)
    }

    private val logging: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    companion object {
        const val APISIGN = "apisign"
        const val ACCEPT = "Accept"
        const val APP_TYPE = "application/json"
        const val BODY = "body"
    }
}
