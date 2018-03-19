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
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.customer.view.FoodFragment
import com.example.kinhangpoon.foodordering.customer.view.MainscreenFragment
import com.example.kinhangpoon.foodordering.customer.view.MenuFragment
import com.example.kinhangpoon.foodordering.customer.view.PlacesFragment
import com.example.kinhangpoon.foodordering.main.register.RegisterFragment
import com.example.kinhangpoon.foodordering.utility.FoodDescription
import com.example.kinhangpoon.foodordering.utility.SendMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SendMessage {

    //var fragmentManager : FragmentManager = null
    internal var myToolbar: Toolbar? = null

    override fun sendData(item_index: Int) {

        val f: Fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        when (f) {
            is MainscreenFragment ->
                when (item_index) {
                    0 -> {
                        Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
                        addPlacesFragment()
                    }
                    1 -> Toast.makeText(this, "Track", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this, "Ordering History", Toast.LENGTH_SHORT).show()
                    3 -> {
                        Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show()
                        startActivity( Intent(this@MainActivity, GMapsActivity::class.java))
                    }

                }
            is MenuFragment -> {
                //Toast.makeText(this, "Food #" + item_index, Toast.LENGTH_SHORT).show()
                addFoodFragment(item_index)
            }


            is PlacesFragment ->
                    //Toast.makeText(this, "Place #" + item_index, Toast.LENGTH_SHORT).show()
                when (item_index) {
                    0 -> {
                        Toast.makeText(this, "High Court of Karnataka", Toast.LENGTH_SHORT).show()
                        addMenuFragment("banglore")
                        FoodDescription.currentCity = "banglore"
                    }
                    1 -> {
                        Toast.makeText(this, "Bangalore Palace", Toast.LENGTH_SHORT).show()
                        addMenuFragment("banglore")
                        FoodDescription.currentCity = "banglore"
                    }
                    2 -> {
                        Toast.makeText(this, "St Francis Xavier", Toast.LENGTH_SHORT).show()
                        addMenuFragment("banglore")
                        FoodDescription.currentCity = "banglore"
                    }
                    3 -> {
                        Toast.makeText(this, "Vikas Soudha", Toast.LENGTH_SHORT).show()
                        addMenuFragment("banglore")
                        FoodDescription.currentCity = "banglore"
                    }
                    4 -> {
                        Toast.makeText(this, "Lotus Tempel", Toast.LENGTH_SHORT).show()
                        addMenuFragment("delhi")
                        FoodDescription.currentCity = "delhi"
                    }
                    5 -> {
                        Toast.makeText(this, "Akshardham", Toast.LENGTH_SHORT).show()
                        addMenuFragment("delhi")
                        FoodDescription.currentCity = "delhi"
                    }
                    6 -> {
                        Toast.makeText(this, "India Gate", Toast.LENGTH_SHORT).show()
                        addMenuFragment("delhi")
                        FoodDescription.currentCity = "delhi"
                    }
                    7 -> {
                        Toast.makeText(this, "Tajmahal", Toast.LENGTH_SHORT).show()
                        addMenuFragment("delhi")
                        FoodDescription.currentCity = "delhi"
                    }
                }
            else -> Toast.makeText(this, "no such fragment " + item_index, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        myToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setSupportActionBar(myToolbar)
        //getSupportActionBar()!!.setTitle("Home")
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            R.id.login -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RegisterFragment())
                    .addToBackStack(null).commit();
            R.id.option_go_home -> {
                Toast.makeText(this, "main screen", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(0, 0)
            }
        }
        return super.onOptionsItemSelected(item)
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

    private fun addPlacesFragment() {
        val placesFragment: PlacesFragment = PlacesFragment()
        placesFragment.setSendMessage(this@MainActivity)

        Log.i("maylog", "inflate places")
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, placesFragment)
                .addToBackStack(null)
                .commit()
    }

    private fun addMenuFragment(city: String) {
        val menuFragment: MenuFragment = MenuFragment()
        menuFragment.setSendMessage(this@MainActivity)
        menuFragment.setCity(city)
        Log.i("maylog", "inflate menu")
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, menuFragment)
                .addToBackStack(null)
                .commit()
    }

    private fun addFoodFragment(item_index: Int) {
        val foodFragment: FoodFragment = FoodFragment()
        foodFragment.setSendMessage(this@MainActivity)
        foodFragment.setCity(FoodDescription.currentCity!!)
        foodFragment.setIndex(item_index)
        Log.i("maylog", "inflate food description")
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, foodFragment)
                .addToBackStack(null)
                .commit()
    }

    override fun setTitle(page_title: String) {
        getSupportActionBar()!!.setTitle(page_title)
    }
}
