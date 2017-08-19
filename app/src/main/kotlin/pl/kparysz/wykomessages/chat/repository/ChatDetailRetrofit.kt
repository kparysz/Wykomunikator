package pl.kparysz.wykomessages.chat.repository

import io.reactivex.Observable
import pl.kparysz.wykomessages.models.pojo.PrivateMessage
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatDetailRetrofit {
    @POST("/pm/conversation/{username}/appkey,{appkey}/userkey,{userkey}")
    fun getChatDetail(@Path("username") userName: String,
                      @Path("appkey") appKey: String,
                      @Path("userkey") userKey: String): Observable<List<PrivateMessage>>
}