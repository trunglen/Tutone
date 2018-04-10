package vn.miraway.tutone.base

import vn.miraway.tutone.injection.component.DaggerPresenterInjector
import vn.miraway.tutone.injection.component.PresenterInjector
import vn.miraway.tutone.modules.ContextModule
import vn.miraway.tutone.modules.NetworkModule
import vn.miraway.tutone.ui.tone.TonePresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    open fun onViewCreated(){}
    open fun onViewDestroyed(){}

    private val injector:PresenterInjector  = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()
    private fun inject(){
        when(this){
            is TonePresenter->injector.inject(this)
        }
    }
    init {
        inject()
    }
}