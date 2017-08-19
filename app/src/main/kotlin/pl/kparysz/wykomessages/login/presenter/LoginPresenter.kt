package pl.kparysz.wykomessages.login.presenter

import io.reactivex.functions.Consumer
import pl.kparysz.wykomessages.base.BasePresenter
import pl.kparysz.wykomessages.login.repository.UserLoginApi
import pl.kparysz.wykomessages.login.view.LoginView
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.rx.SubscriptionApi

class LoginPresenter(val wykopPreferencesApi: WykopPreferencesApi,
                     val subscriptionManager: SubscriptionApi,
                     val userLoginApi: UserLoginApi) : BasePresenter<LoginView>() {

    companion object {
        const val SPLIT_CHAR = "/"
        const val TOKEN_POSITION = 2
    }

    fun getToken(url: String?) {
        val splitUrl = url?.split(SPLIT_CHAR)?.takeLast(TOKEN_POSITION) ?: emptyList()
        if (splitUrl.size > 1) {
            val token = splitUrl[0]
            wykopPreferencesApi.saveUserToken(token)
            loginUser(token)
        } else {
            baseView?.showError()
        }

    }

    private fun loginUser(token: String) {
        subscriptionManager.subscribe(userLoginApi.loginUser(token),
                Consumer { baseView?.openMessagesActivity() },
                Consumer { baseView?.showError() },
                this)
    }

    override fun destroyView() {
        super.destroyView()
        subscriptionManager.dispose(this)
    }
}