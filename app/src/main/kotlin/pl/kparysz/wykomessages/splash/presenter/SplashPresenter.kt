package pl.kparysz.wykomessages.splash.presenter

import pl.kparysz.wykomessages.base.BasePresenter
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.splash.view.SplashView

class SplashPresenter(val wykopPreferencesApi: WykopPreferencesApi) : BasePresenter<SplashView>() {

    override fun setView(view: SplashView) {
        super.setView(view)
        baseView?.showProgress()
        checkIfUserLogged()
    }

    private fun checkIfUserLogged() {
        if (wykopPreferencesApi.isUserLogged()) {
            baseView?.openMessagesActivity()
        } else {
            baseView?.openLoginActivity()
        }
        baseView?.hideProgress()
    }

}