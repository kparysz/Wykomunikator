package pl.kparysz.wykomessages.chat.view

import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail

interface ChatView {
    fun showChat(messages: List<PrivateMessageDetail>)
    fun showError()
}