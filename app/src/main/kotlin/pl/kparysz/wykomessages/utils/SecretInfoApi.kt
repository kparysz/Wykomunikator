package pl.kparysz.wykomessages.utils

interface SecretInfoApi {
    fun getSecretAppKey(): String
    fun getAppKey(): String
}