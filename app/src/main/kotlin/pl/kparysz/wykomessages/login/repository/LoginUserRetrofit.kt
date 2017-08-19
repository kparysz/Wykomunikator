package pl.kparysz.wykomessages.login.repository

import io.reactivex.Observable
import pl.kparysz.wykomessages.models.pojo.UserProfile
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginUserRetrofit {
    @FormUrlEncoded
    @POST("/user/login/appkey,{appkey}")
    fun loginUser(@Field("accountkey") accountkey: String, @Path("appkey") appkey: String): Observable<UserProfile>
}