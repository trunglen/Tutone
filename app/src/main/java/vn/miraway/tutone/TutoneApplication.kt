package vn.miraway.tutone

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import vn.miraway.tutone.injection.component.AppComponent
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.network.ToneApi
import javax.inject.Inject
import javax.inject.Named

class TutoneApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent;
        @JvmStatic var injector = DaggerAppComponent.builder()
                .restModule(RestModule())
                .build()
    }

    @Inject
    lateinit var toneApi: ToneApi
    lateinit var realm: Realm
    @field:[Inject Named("something")]
    lateinit var something:String
    override fun onCreate() {
        super.onCreate()
        injector.inject(this)
        //config fr realm
        var c = Realm.init(this)
        val config = RealmConfiguration.Builder()
        config.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(config.build())
        Stetho.initializeWithDefaults(this)
//        cache tone by realm
        cacheTones()
    }

    fun cacheTones() {
        toneApi.getTones()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate {
                    Log.d("[application_caching]", "stop cache")
                }
                .map { res -> res.data }
                .subscribe(
                        { res ->
                            //example
                            realm = Realm.getDefaultInstance()
                            realm.beginTransaction()
                            realm.copyToRealmOrUpdate(res)
                            realm.commitTransaction()
                        },
                        { err -> Log.d("response_err", err.message) }
                )
    }
}