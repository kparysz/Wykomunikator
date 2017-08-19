package pl.kparysz.wykomessages.messages.repository

import io.reactivex.Observable
import pl.kparysz.wykomessages.models.pojo.Conversation
import retrofit2.http.POST
import retrofit2.http.Path

interface ConversationListRetrofit {
    @POST("/pm/conversationslist/appkey,{appkey}/userkey,{userkey}")
    fun getAllMessages(@Path("appkey") appkey: String,
                       @Path("userkey") userKey: String): Observable<List<Conversation>>
}


