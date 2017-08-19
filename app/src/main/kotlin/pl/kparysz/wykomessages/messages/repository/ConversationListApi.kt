package pl.kparysz.wykomessages.messages.repository

import io.reactivex.Observable
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail

interface ConversationListApi {
    fun getAllMessages(): Observable<List<ConversationDetail>>
}