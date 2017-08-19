package pl.kparysz.wykomessages.base

open class BasePresenter<V> : BaseView<V> {
    var baseView: V? = null

    override fun setView(view: V) {
        this.baseView = view
    }

    override fun destroyView() {
        this.baseView = null
    }

}
