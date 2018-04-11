package vn.miraway.tutone

import android.app.Application
import android.util.Log
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.modules.RestModule

class TutoneApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder()
                .restModule(RestModule())
                .build()
    }

    fun getAppComponent() {

    }
}