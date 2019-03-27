package com.zllbird.choicequestion

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.zllbird.choicequestion.choice.CreateActionActivity
import com.zllbird.choicequestion.plan.CreateTomorrowPlanActivity
import com.zllbird.choicequestion.plan.DayPlanFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val CREATE_ACTION = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        loadToggle()
        loadNav()
        loadLintner()
        loadFragment()
    }

    private fun loadFragment() {
        fragmentManager.beginTransaction().add(R.id.content_main,DayPlanFragment.newInstance()).commit()
    }

    private fun loadLintner() {
        // 跳转到创建行动条目
//        fab.setOnClickListener { startActivityForResult<CreateActionActivity>(CREATE_ACTION) }
        fab.setOnClickListener { startActivityForResult<CreateTomorrowPlanActivity>(CREATE_ACTION) }
    }

    private fun loadNav(){
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun loadToggle(){
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

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
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
