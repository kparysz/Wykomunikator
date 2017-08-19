package wykomessages.di.modules

import pl.kparysz.wykomessages.di.modules.NetworkModule

class AutomatedNetworkModule(override var baseUrl: String) : NetworkModule(baseUrl)