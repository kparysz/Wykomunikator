package pl.kparysz.wykomessages.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.di.App
import javax.inject.Inject

class SettingsActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun createIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    @Inject
    lateinit var presenter: SettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auto_refresh_chat.setOnCheckedChangeListener { _, isChecked -> presenter.autoRefreshSwitched(isChecked) }
    }
}
