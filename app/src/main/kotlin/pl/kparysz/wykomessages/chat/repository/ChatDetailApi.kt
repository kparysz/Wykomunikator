package pl.kparysz.wykomessages.chat.repository

import io.reactivex.Observable
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail

interface ChatDetailApi {
    fun getChatDetail(userName: String): Observable<List<PrivateMessageDetail>>
    fun sendMessage(recipientName: String, messageBody: String): Observable<Any>
}