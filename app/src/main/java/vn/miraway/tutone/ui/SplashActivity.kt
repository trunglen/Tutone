package vn.miraway.tutone.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import vn.miraway.tutone.R
import vn.miraway.tutone.views.activities.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            run {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        },5000)
    }
}
