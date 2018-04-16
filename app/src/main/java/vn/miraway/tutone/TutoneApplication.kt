package vn.miraway.tutone

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.modules.RestModule

class TutoneApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder()
                .restModule(RestModule())
                .build()
        var c = Realm.init(this)
        val config = RealmConfiguration.Builder().name("tone").build()
        Realm.setDefaultConfiguration(config)
    }

    fun getAppComponent() {

    }
}