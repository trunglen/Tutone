package vn.miraway.tutone.injection.component

import android.app.Activity
import dagger.Component
import retrofit2.Retrofit
import vn.miraway.tutone.TutoneApplication
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.network.ToneApi
import vn.miraway.tutone.ui.SplashActivity
import vn.miraway.tutone.ui.tone.ToneFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RestModule::class))
public interface AppComponent {
    fun retrofit(): Retrofit
    fun injectFragment(toneFragment: ToneFragment)
    fun injectApplication(application: TutoneApplication)
    fun injectActivity(activity: SplashActivity)
    fun toneApi(): ToneApi
}