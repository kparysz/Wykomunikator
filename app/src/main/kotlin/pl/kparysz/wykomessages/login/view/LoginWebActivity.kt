package pl.kparysz.wykomessages.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.*
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.login.presenter.LoginPresenter
import pl.kparysz.wykomessages.network.WykopConfig
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi
import javax.inject.Inject

class LoginWebActivity : AppCompatActivity(), LoginView {

    companion object {
        @JvmStatic
        fun createIntent(context: Context) = Intent(context, LoginWebActivity::class.java)
    }

    @Inject
    lateinit var presenter: LoginPresenter
    @Inject
    lateinit var navigator: NavigatorApi

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_web_view)
        webView.loadUrl(WykopConfig.REGISTER_APP_LINK + WykopConfig.API_APP_KEY + "/")
        presenter.setView(this)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        CookieManager.getInstance().removeAllCookie()
        webView.setWebViewClient(MyWebViewClient())
        webView.setWebChromeClient(object : WebChromeClient() {
            override fun onConsoleMessage(cm: ConsoleMessage?): Boolean {
                return true
            }
        })
    }

    override fun showError() {

    }

    override fun openMessagesActivity() {
        val returnIntent = Intent()
        setResult(1212, returnIntent)
        navigator.openMessagesActivity(this)
        finish()
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            presenter.getToken(url)
            return true
        }

        override fun onReceivedHttpAuthRequest(view: WebView,
                                               handler: HttpAuthHandler, host: String, realm: String) {

        }

    }
}
