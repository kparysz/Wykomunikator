package pl.kparysz.wykomessages.rx

import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface SubscriptionApi {

    fun <C> subscribe(observable: Observable<C>,
                      onNextAction: Consumer<C>,
                      onErrorAction: Consumer<Throwable>,
                      subscriber: Any)

    fun dispose(subscriber: Any): Int
}