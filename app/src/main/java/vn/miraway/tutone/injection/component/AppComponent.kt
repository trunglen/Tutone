package vn.miraway.tutone.injection.component

import android.app.Application
import dagger.Component
import retrofit2.Retrofit
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.network.ToneApi
import vn.miraway.tutone.ui.tone.ToneFragment
import vn.miraway.tutone.views.fragments.BaseFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RestModule::class))
public interface AppComponent {
    fun retrofit():Retrofit
    fun injectFragment(toneFragment: ToneFragment)
    fun inject(application: Application)
    fun toneApi():ToneApi
}