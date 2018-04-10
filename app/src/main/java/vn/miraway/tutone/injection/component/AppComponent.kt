package vn.miraway.tutone.injection.component

import dagger.Component
import retrofit2.Retrofit
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.network.ToneApi
import vn.miraway.tutone.ui.tone.ToneFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RestModule::class))
public interface AppComponent {
    fun retrofit():Retrofit
    fun inject(toneFragment:ToneFragment)
    fun toneApi():ToneApi
}