package vn.miraway.tutone.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_splash.*
import vn.miraway.tutone.R
import vn.miraway.tutone.TutoneApplication
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.modules.RestModule
import vn.miraway.tutone.network.ToneApi
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var toneApi: ToneApi
    var realm: Realm = Realm.getDefaultInstance()
    private val injector = DaggerAppComponent.builder()
            .restModule(RestModule())
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        TutoneApplication.injector.injectActivity(this)
        cacheTones()
        val handler = Handler()
        handler.postDelayed({
            run {
                pgbLoading.visibility = View.INVISIBLE
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }, 5000)
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
                            realm.beginTransaction()
                            realm.copyToRealmOrUpdate(res)
                            realm.commitTransaction()
                        },
                        { err -> Log.d("response_err", err.message) }
                )
    }
}
