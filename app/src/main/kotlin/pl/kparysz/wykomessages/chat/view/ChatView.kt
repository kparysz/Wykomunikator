package pl.kparysz.wykomessages.chat.view

import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail

interface ChatView {
    fun showChat(messages: List<PrivateMessageDetail>)
    fun messageSent()
    fun showMessageSendError()
    fun showError()
    fun showProgress()
    fun hideProgress()
}