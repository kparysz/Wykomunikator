package pl.kparysz.wykomessages.utils

import pl.kparysz.wykomessages.network.WykopConfig

class SecretInfo : SecretInfoApi {
    override fun getSecretAppKey() = WykopConfig.API_APP_SECRET_KEY
    override fun getAppKey() = WykopConfig.API_APP_KEY
}