package pl.kparysz.wykomessages.prefs

import `in`.co.ophio.secure.core.KeyStoreKeyGenerator
import `in`.co.ophio.secure.core.ObscuredPreferencesBuilder
import android.content.SharedPreferences
import pl.kparysz.wykomessages.di.App
import java.io.IOException
import java.security.GeneralSecurityException

class WykopPreferences(val context: App) : WykopPreferencesApi {

    private val PREFS_NAME = "WykomunikatorPreferences"
    private val USER_TOKEN = "USER_TOKEN"
    private val USER_KEY = "USER_KEY"
    private val AUTO_REFRESH = "USER_NAME"
    private var sharedPreferences: SharedPreferences

    init {
        val secret = getKey()
        sharedPreferences = ObscuredPreferencesBuilder()
                .setApplication(context)
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(PREFS_NAME)
                .setSecret(secret)
                .createSharedPrefs()
    }

    override fun setAutoRefresh(state: Boolean) {
        sharedPreferences.edit().putBoolean(AUTO_REFRESH, state).apply()
    }

    override fun getAutoRefreshState() = sharedPreferences.getBoolean(AUTO_REFRESH, false)

    override fun getUserToken() = sharedPreferences.getString(USER_TOKEN, "")

    override fun saveUserKey(userKey: String) {
        sharedPreferences.edit().putString(USER_KEY, userKey).apply()
    }

    override fun getUserKey() = sharedPreferences.getString(USER_KEY, "")

    override fun saveUserToken(token: String) {
        sharedPreferences.edit().putString(USER_TOKEN, token).apply()
    }

    override fun isUserLogged() = sharedPreferences.getString(USER_TOKEN, "").isNotEmpty()

    override fun logoutUser() {
        sharedPreferences.edit().putString(USER_TOKEN, "").apply()
    }

    private fun getKey(): String {
        var secret = ""
        try {
            secret = getKeyGenerator().loadOrGenerateKeys()
        } catch (e: GeneralSecurityException) {
        } catch (e: IOException) {
        }

        return secret
    }

    private fun getKeyGenerator() =
            KeyStoreKeyGenerator.get(context, context.packageName)
}