package pl.kparysz.wykomessages.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import pl.kparysz.wykomessages.network.WykopHttpClient
import pl.kparysz.wykomessages.utils.SecretInfoApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class NetworkModule(open var baseUrl: String) {

    @Provides
    fun providesSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("TEST", Context.MODE_PRIVATE)
    }

    @Provides
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    open fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
    }

    @Provides
    internal fun providesWykopHttpClient(secretInfoApi: SecretInfoApi): WykopHttpClient {
        return WykopHttpClient(secretInfoApi)
    }

}
