package pl.kparysz.wykomessages.chat.presenter

import io.reactivex.functions.Consumer
import pl.kparysz.wykomessages.base.BasePresenter
import pl.kparysz.wykomessages.chat.repository.ChatDetailApi
import pl.kparysz.wykomessages.chat.view.ChatView
import pl.kparysz.wykomessages.rx.SubscriptionApi

class ChatPresenter(val subscriptionManager: SubscriptionApi,
                    val chatDetailApi: ChatDetailApi) : BasePresenter<ChatView>() {

    fun downloadChat(userName: String) {
        subscriptionManager.subscribe(chatDetailApi.getChatDetail(userName),
                Consumer { baseView?.showChat(it) },
                Consumer { baseView?.showError() },
                this)
    }

    override fun destroyView() {
        super.destroyView()
        subscriptionManager.dispose(this)
    }

}