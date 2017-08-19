package pl.kparysz.wykomessages

import io.reactivex.schedulers.Schedulers
import pl.kparysz.wykomessages.rx.SubscriptionManager

class SubscriptionManagerTest : SubscriptionManager(Schedulers.trampoline(), Schedulers.trampoline())