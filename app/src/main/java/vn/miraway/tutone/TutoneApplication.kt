package vn.miraway.tutone

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import io.realm.Realm
import io.realm.RealmConfiguration
import vn.miraway.tutone.injection.component.AppComponent
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.model.Tone
import vn.miraway.tutone.modules.RestModule

class TutoneApplication:Application() {
    var appComponent :AppComponent? = null;
    override fun onCreate() {
        super.onCreate()
         appComponent = DaggerAppComponent.builder()
                .restModule(RestModule())
                .build()
                //config fr realm
        var c = Realm.init(this)
        val config = RealmConfiguration.Builder()
        config.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(config.build())
        Stetho.initializeWithDefaults(this);

        //example
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(listOf(Tone("dsad","ds","","","")))
        realm.commitTransaction()
    }

    fun cacheTones() {
        appComponent?.injectRetrofit(this)
    }
}