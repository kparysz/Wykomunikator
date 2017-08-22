package pl.kparysz.wykomessages.chat.presenter

import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import pl.kparysz.wykomessages.base.BasePresenter
import pl.kparysz.wykomessages.chat.repository.ChatDetailApi
import pl.kparysz.wykomessages.chat.view.ChatView
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.rx.SubscriptionApi

class ChatPresenter(private val subscriptionManager: SubscriptionApi,
                    private val wykopPreferencesApi: WykopPreferencesApi,
                    private val chatDetailApi: ChatDetailApi) : BasePresenter<ChatView>() {

    companion object {
        const val REFRESH_CHAT_INTERVAL = 10
    }

    var userName = ""

    override fun setView(view: ChatView) {
        super.setView(view)
        subscribeOnAutoRefresh()
    }

    private fun subscribeOnAutoRefresh() {
        if (wykopPreferencesApi.getAutoRefreshState().not()) {
            return
        }

        subscriptionManager.subscribeCyclicWithDelay(chatDetailApi.getChatDetail(userName),
                Consumer { },
                Consumer { baseView?.hideProgress() },
                Action { downloadChat() },
                REFRESH_CHAT_INTERVAL,
                this,
                REFRESH_CHAT_INTERVAL)

    }

    fun downloadChat() {
        subscriptionManager.subscribe(chatDetailApi.getChatDetail(userName),
                Consumer {
                    baseView?.showChat(it)
                    baseView?.hideProgress()
                },
                Consumer {
                    baseView?.showError()
                    baseView?.hideProgress()
                },
                this)
    }

    override fun destroyView() {
        super.destroyView()
        subscriptionManager.dispose(this)
    }

    fun sendMessage(userName: String, messageBody: String) {
        subscriptionManager.subscribe(chatDetailApi.sendMessage(userName, messageBody),
                Consumer { baseView?.messageSent() },
                Consumer { baseView?.showMessageSendError() },
                this)
    }

}