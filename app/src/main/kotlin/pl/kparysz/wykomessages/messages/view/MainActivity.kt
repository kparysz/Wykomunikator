package pl.kparysz.wykomessages.messages.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_messages.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.messages.adapters.ConversationsAdapter
import pl.kparysz.wykomessages.messages.presenter.MainPresenter
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MessagesView {
    companion object {
        @JvmStatic
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var navigator: NavigatorApi

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        presenter.setView(this)
        navigation.setOnNavigationItemSelectedListener { menuItem -> setNavigationListener(menuItem) }
    }

    private fun setNavigationListener(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_home -> presenter.downloadAllConversations()
            R.id.action_search -> navigator.openSearchActivity(this)
            R.id.action_settings -> navigator.openSettingsActivity(this)
        }
        return true
    }

    override fun getContext() = this

    override fun showMessages(messages: List<ConversationDetail>) {
        val adapter = ConversationsAdapter(this)
        messages_list.adapter = adapter
        messages_list.layoutManager = LinearLayoutManager(this)
        adapter.conversationList += messages
    }

    override fun showError() {
        Snackbar.make(messages_list, getString(R.string.get_messages_failed), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snackbar_retry), {})
                .setActionTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .show()
    }
}
