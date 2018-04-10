package vn.miraway.tutone.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import vn.miraway.tutone.base.BaseView

@Module
object ContextModule {
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context):Application{
        return context.applicationContext as Application
    }
}