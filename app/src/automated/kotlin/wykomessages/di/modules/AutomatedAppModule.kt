package wykomessages.di.modules

import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.di.modules.AppModule
import pl.kparysz.wykomessages.utils.SecretInfoApi
import wykomessages.di.fake.FakeSecretInfo

class AutomatedAppModule(var app: App) : AppModule(app) {

    override fun providesSecretInfoApi(): SecretInfoApi {
        return FakeSecretInfo()
    }

}