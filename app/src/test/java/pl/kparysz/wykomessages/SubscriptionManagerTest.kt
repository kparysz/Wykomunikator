package pl.kparysz.wykomessages

import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import pl.kparysz.wykomessages.rx.SubscriptionManager
import java.util.concurrent.TimeUnit

class SubscriptionManagerTest(private var testScheduler: TestScheduler = TestScheduler()) : SubscriptionManager(Schedulers.trampoline(), Schedulers.trampoline()) {

    override fun <C> subscribeCyclicWithDelay(observable: Observable<C>,
                                              onNextAction: Consumer<C>,
                                              onErrorAction: Consumer<Throwable>,
                                              onTickAction: Action,
                                              intervalSeconds: Int,
                                              subscriber: Any,
                                              initialDelayInSeconds: Int) {

        val intervalObservable = Observable
                .interval(initialDelayInSeconds.toLong(), intervalSeconds.toLong(), TimeUnit.SECONDS, testScheduler)
                .observeOn(testScheduler)
                .doOnNext { onTickAction.run() }
                .flatMap { observable }

        subscribe(
                intervalObservable,
                onNextAction,
                onErrorAction,
                subscriber)
    }
}