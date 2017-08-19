package wykomessages.di.fake

import pl.kparysz.wykomessages.utils.SecretInfoApi

class FakeSecretInfo : SecretInfoApi {
    override fun getSecretAppKey() = "FAKE_SECRET_APP_KEY"

    override fun getAppKey() = "FAKE_APP_KEY"
}