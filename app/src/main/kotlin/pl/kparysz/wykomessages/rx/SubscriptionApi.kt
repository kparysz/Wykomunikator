package pl.kparysz.wykomessages.rx

import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

interface SubscriptionApi {

    fun <C> subscribe(observable: Observable<C>,
                      onNextAction: Consumer<C>,
                      onErrorAction: Consumer<Throwable>,
                      subscriber: Any)

    fun <C> subscribeCyclicWithDelay(observable: Observable<C>,
                                                   onNextAction: Consumer<C>,
                                                   onErrorAction: Consumer<Throwable>,
                                                   onTickAction: Action,
                                                   intervalSeconds: Int,
                                                   subscriber: Any,
                                                   initialDelayInSeconds: Int)

    fun dispose(subscriber: Any): Int
}