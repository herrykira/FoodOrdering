package com.example.kinhangpoon.foodordering.main

import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.customer.view.MainscreenFragment
import com.example.kinhangpoon.foodordering.main.register.RegisterFragment
import com.example.kinhangpoon.foodordering.utility.SendMessage
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SendMessage {
    //var fragmentManager : FragmentManager = null
    override fun sendData(item_index: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val f: Fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        when (f) {
            is MainscreenFragment ->
                when (item_index) {
                    0 -> Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this, "Track", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this, "Ordering History", Toast.LENGTH_SHORT).show()
                    3 -> startActivity( Intent(this@MainActivity, GMapsActivity::class.java))
                }

            else -> Toast.makeText(this, "no such fragment " + item_index, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        addFrontpageFragment()
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
//        var userRegistered:Boolean = false
//        if(LoginActivity.mAuth==null){
//            userRegistered = false;
//        }
//        else{
//            userRegistered = true;
//        }
//        menu.findItem(R.id.register).setVisible(!userRegistered)
//        menu.findItem(R.id.login).setVisible(!userRegistered)
//        menu.findItem(R.id.logout).setVisible(userRegistered)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.register -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RegisterFragment())
                    .addToBackStack(null).commit();
            R.id.login ->userlogin();
            R.id.logout -> revokeAccess();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun userlogin() {
        startActivity(Intent(this@MainActivity,LoginActivity::class.java))
    }

    private fun revokeAccess() {
        // Firebase sign out
        LoginActivity.mAuth?.signOut()
        LoginActivity.mGoogleSignInClient?.revokeAccess()?.addOnCompleteListener(this) {  }
        finish();
        startActivity(getIntent());
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

    private fun addFrontpageFragment() {
        val mainscreenFragment: MainscreenFragment = MainscreenFragment()
        mainscreenFragment.setSendMessage(this@MainActivity)

        Log.i("maylog", "inflate mainscreen")
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mainscreenFragment)
                .addToBackStack(null)
                .commit()

        //var fragmentManager : FragmentManager
        //fragmentTransaction = fragmentManager.beginTransaction()

        //frontpageFragment = FrontpageFragment()


        //fragmentTransaction.replace(R.id.fragmentContainer, frontpageFragment)//in CountriesFragment import android.support.v4.app.Fragment;
        //fragmentTransaction.commit()
        //Log.i("mylog", "add list");
    }
}
