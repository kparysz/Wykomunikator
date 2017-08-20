package pl.kparysz.wykomessages.splash.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_splash.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.splash.presenter.SplashPresenter
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenter
    @Inject
    lateinit var navigator: NavigatorApi

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.setView(this)
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun openLoginActivity() {
        navigator.openLoginWebActivity(this, 1212)
        finish()
    }

    override fun openMessagesActivity() {
        navigator.openMessagesActivity(this)
        finish()
    }
}
