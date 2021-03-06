package vn.miraway.tutone.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import io.realm.Realm
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.ui.tone.ToneFragment

open class BaseFragment : Fragment() {
    private val injector = DaggerAppComponent.builder()
            .restModule(RestModule())
            .build()
    var realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private fun inject() {
        when (this) {
            is ToneFragment -> injector.injectFragment(this)
        }
    }

    fun stopLoading() {}
}