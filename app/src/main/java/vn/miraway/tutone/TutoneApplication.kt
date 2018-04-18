package vn.miraway.tutone

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import vn.miraway.tutone.hub.Hub
import vn.miraway.tutone.injection.component.AppComponent
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.network.ToneApi
import javax.inject.Inject

class TutoneApplication : Application() {

    companion object {
        @JvmStatic
        var injector = DaggerAppComponent.builder()
                .restModule(RestModule())
                .build()
        var hub = Hub()
    }

    @Inject
    lateinit var toneApi: ToneApi
    lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()
        //config fr realm
        initRealm()
        Stetho.initializeWithDefaults(this)
    }


    fun initRealm() {
        var c = Realm.init(this)
        val config = RealmConfiguration.Builder()
        config.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(config.build())
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    fun injector(): AppComponent {
        return injector
    }
}