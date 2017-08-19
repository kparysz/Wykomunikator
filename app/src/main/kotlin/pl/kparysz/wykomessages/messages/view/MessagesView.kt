package pl.kparysz.wykomessages.messages.view

import android.content.Context
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail

interface MessagesView {
    fun showMessages(messages: List<ConversationDetail>)
    fun showError()
    fun getContext(): Context

}