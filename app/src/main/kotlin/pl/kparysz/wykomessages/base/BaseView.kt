package pl.kparysz.wykomessages.base

interface BaseView<V> {

    fun setView(view: V)

    fun destroyView()

}
