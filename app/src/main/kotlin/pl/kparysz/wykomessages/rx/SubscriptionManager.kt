package pl.kparysz.wykomessages.rx

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.*

open class SubscriptionManager(private val observingScheduler: Scheduler,
                               private val executingScheduler: Scheduler) : SubscriptionApi {
    private val subscriptions = HashMap<String, MutableList<Disposable>>()

    override fun <C> subscribe(observable: Observable<C>, onNextAction: Consumer<C>, onErrorAction: Consumer<Throwable>, subscriber: Any) {
        val objectSubscriptions = getSubscriptions(subscriber)
        objectSubscriptions.add(observable
                .observeOn(observingScheduler)
                .subscribeOn(executingScheduler)
                .subscribe(onNextAction, onErrorAction))
    }

    private fun getSubscriberTag(subscriber: Any): String {
        return subscriber.toString()
    }

    private fun getSubscriptions(subscriber: Any): MutableList<Disposable> {
        val tag = getSubscriberTag(subscriber)
        var objectSubscriptions: MutableList<Disposable>? = subscriptions[tag]
        if (objectSubscriptions == null) {
            objectSubscriptions = ArrayList<Disposable>()
            subscriptions.put(tag, objectSubscriptions)
        }
        return objectSubscriptions
    }

    override fun dispose(subscriber: Any): Int {
        val tag = getSubscriberTag(subscriber)

        if (!subscriptions.containsKey(tag)) {
            return 0
        }

        val taggedSubscriptions =
                if (this.subscriptions.containsKey(tag))
                    this.subscriptions[tag]!!
                else
                    mutableListOf()

        val numberOfSubscriptions = taggedSubscriptions.size
        for (subscription in taggedSubscriptions) {
            subscription.dispose()
        }
        subscriptions.remove(tag)
        return numberOfSubscriptions
    }

}