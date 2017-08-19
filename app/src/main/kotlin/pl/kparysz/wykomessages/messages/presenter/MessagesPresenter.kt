package pl.kparysz.wykomessages.messages.presenter

import io.reactivex.functions.Consumer
import pl.kparysz.wykomessages.base.BasePresenter
import pl.kparysz.wykomessages.messages.repository.ConversationListApi
import pl.kparysz.wykomessages.messages.view.MessagesView
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail
import pl.kparysz.wykomessages.rx.SubscriptionApi
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi

class MessagesPresenter(val subscriptionManager: SubscriptionApi,
                        val navigatorApi: NavigatorApi,
                        val conversationListApi: ConversationListApi) : BasePresenter<MessagesView>() {

    override fun setView(view: MessagesView) {
        super.setView(view)
        getAllMessages()
    }

    private fun getAllMessages() {
        subscriptionManager.subscribe(conversationListApi.getAllMessages(),
                Consumer {
                    addClickActionToConversation(it)
                    baseView?.showMessages(it)
                },
                Consumer { baseView?.showError() },
                this)
    }

    private fun addClickActionToConversation(conversationsDetail: List<ConversationDetail>) {
        conversationsDetail.forEach { it.apply { onClickAction = { navigatorApi.openChatActivity(baseView?.getContext(), it.authorName) } } }
    }

    override fun destroyView() {
        super.destroyView()
        subscriptionManager.dispose(this)
    }

}
