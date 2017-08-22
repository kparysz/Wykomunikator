package pl.kparysz.wykomessages.settings

import pl.kparysz.wykomessages.prefs.WykopPreferencesApi

class SettingsPresenter(val wykopPreferencesApi: WykopPreferencesApi){

    fun autoRefreshSwitched(state: Boolean){
        wykopPreferencesApi.setAutoRefresh(state)
    }
}