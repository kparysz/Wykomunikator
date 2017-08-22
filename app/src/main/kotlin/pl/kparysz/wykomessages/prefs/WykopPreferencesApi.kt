package pl.kparysz.wykomessages.prefs

interface WykopPreferencesApi {
    fun saveUserToken(token: String)
    fun saveUserKey(userKey: String)
    fun getUserToken(): String
    fun getUserKey(): String
    fun isUserLogged(): Boolean
    fun logoutUser()
    fun setAutoRefresh(state: Boolean)
    fun getAutoRefreshState(): Boolean
}