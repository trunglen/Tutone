package vn.miraway.tutone.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import vn.miraway.tutone.R
import vn.miraway.tutone.ui.tone.ToneFragment
import vn.miraway.tutone.model.Tone


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        showToneFragment("recent")
    }

    var realm = Realm.getDefaultInstance()
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_children -> {
                showToneFragment("children")
            }
            R.id.nav_country -> {
                showToneFragment("country")
            }
            R.id.nav_latin -> {
                showToneFragment("latin")
            }
            R.id.nav_dancing -> {
                showToneFragment("dance")
            }
            R.id.navigation_featured -> {
                showToneFragment("featured")
            }
            R.id.navigation_popular -> {
                showToneFragment("popular")
            }
            R.id.navigation_recent-> {
                showToneFragment("recent")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private lateinit var toneFragment :ToneFragment

    fun passCategory(category: String) {
        var bundle = Bundle()
        bundle.putString("category", category)
        toneFragment.arguments = bundle
    }

    fun showToneFragment(category:String) {
        toneFragment = ToneFragment()
        passCategory(category)
        supportFragmentManager.beginTransaction().replace(R.id.layoutHolder, toneFragment).commit()
    }

}
