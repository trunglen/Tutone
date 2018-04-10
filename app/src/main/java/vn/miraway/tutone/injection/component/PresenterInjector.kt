package vn.miraway.tutone.injection.component

import dagger.BindsInstance
import dagger.Component
import vn.miraway.tutone.base.BaseView
import vn.miraway.tutone.modules.ContextModule
import vn.miraway.tutone.modules.NetworkModule
import vn.miraway.tutone.ui.tone.TonePresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ContextModule::class, NetworkModule::class))
interface PresenterInjector {
    fun inject(tonePresenter: TonePresenter)
    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector
        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}