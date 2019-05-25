package com.centosquare

import android.R.id.home
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import com.centosquare.Fragments.*

class NavigationDrawerActivity : AppCompatActivity() ,View.OnClickListener {



    //Declaring essential varriables and initializing it will null value using ?
    internal var humbburger: ImageView? = null
    internal var mDrawerLayout: DrawerLayout? = null
    internal var backButton: ImageView? = null
    internal var shareapp: TextView? = null
    private var drawerLayout: DrawerLayout? = null
    private var fragment: Fragment? = null
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer_activity)




        //set custom toolbar as action bar and set the visibilty to gone
        val toolbar:android.support.v7.widget.Toolbar = findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar
        var mTitle:TextView = toolbar.findViewById(R.id.toolbar_title)
        setSupportActionBar(toolbar)
        toolbar.visibility=View.GONE


        //getting primary mosque from shared preference
        val sharedPreferences = this.getSharedPreferences("GetPrimaryMosque", MODE_PRIVATE)
        var primaryId = sharedPreferences.getInt("PM_ID",0)
        val primaryName = sharedPreferences.getString("PM_NAME","")

        Toast.makeText(applicationContext, "Primary Mosque ID:" + primaryId, Toast.LENGTH_LONG).show()
        Toast.makeText(applicationContext, "Primary Mosque Name:" + primaryName, Toast.LENGTH_LONG).show()

        Log.d("eminumber",""+primaryId)

        var sharedPreferences2 = this.getSharedPreferences("LatLang", MODE_PRIVATE)
        var longitude = sharedPreferences2.getString("Lag","")
        val latitude = sharedPreferences2.getString("Lat","")

        Log.d("longitudeMain",""+longitude)
        Log.d("latitudeMain",""+latitude)








        //this will get the reference of share button in navigation drawer and set the onclick listener
        shareapp = findViewById(R.id.text12) as TextView
        shareapp?.setOnClickListener(View.OnClickListener {

            //it will get the system screen for sharing content
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "Here is the share content body"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here")
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        })


        //initializing back button , humburger button and drawer layout
        backButton = findViewById(R.id.backButton) as ImageView
        humbburger = findViewById(R.id.humburgerIcon) as ImageView
        mDrawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout

        //setting click listener for humburger icon button
        humbburger?.setOnClickListener(View.OnClickListener {

            //it will show drawer layout from right side of the screen
            mDrawerLayout?.openDrawer(Gravity.RIGHT)
        })


        //this method will show the home fragment when the activity is created
        showHomeOnFirstTime()



        //setting the click listener for back button and showing home fragment on click
        backButton?.setOnClickListener(View.OnClickListener {
            showHome()
        })


        //getting reference of first item in app drawer and set onclick listener
        val home = findViewById(R.id.text1) as TextView;
        home.setOnClickListener(this)


        //getting reference of eleventh item in app drawer and set onclick listener
        val Feedback = findViewById(R.id.text11) as TextView
        Feedback.setOnClickListener(this)


        //getting reference of ninth item in app drawer and set onclick listener
        val Dua = findViewById(R.id.text9) as TextView
        Dua.setOnClickListener(this)


        //getting reference of third item in app drawer and set onclick listener
        val fragmentmassajilist = findViewById(R.id.text3) as TextView
        fragmentmassajilist.setOnClickListener(this)


        //getting reference of fourth item in app drawer and set onclick listener
        val fragmentnearestmassajid= findViewById(R.id.text4) as TextView
        fragmentnearestmassajid.setOnClickListener(this)


        //getting reference of fifth item in app drawer and set onclick listener
        val fragmentnearestjummah = findViewById(R.id.text5) as TextView
        fragmentnearestjummah.setOnClickListener(this)


        //getting reference of tenth item in app drawer and set onclick listener
        val fragmentsettings= findViewById(R.id.text10) as TextView
        fragmentsettings.setOnClickListener(this)


        //getting reference of seventh item in app drawer and set onclick listener
        val fragmentnotification= findViewById(R.id.text7) as TextView
        fragmentnotification.setOnClickListener(this)


        //getting reference of eight item in app drawer and set onclick listener
        val fragmentaddmosque= findViewById(R.id.text8) as TextView
        fragmentaddmosque.setOnClickListener(this)


        //getting reference of sixth item in app drawer and set onclick listener
        val fragqibla = findViewById(R.id.text6) as TextView
        fragqibla.setOnClickListener(this)


        //getting reference of second item in app drawer and set onclick listener
        val fragmentfavorite = findViewById(R.id.text2) as TextView
        fragmentfavorite.setOnClickListener(this)

        //initializing other drawer layout reference
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout

        //initializing actionbar toggle overriding essential methods and set to the drawer listener of drawer layout
        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                if (item != null && item.itemId == android.R.id.home) {
                    if (drawerLayout!!.isDrawerOpen(Gravity.RIGHT)) {
                        drawerLayout?.closeDrawer(Gravity.RIGHT)
                    } else {
                        drawerLayout?.openDrawer(Gravity.RIGHT)
                    }
                }
                return false
            }
        }
        drawerLayout?.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


    }

//this method will handle the back press of system back button
    override fun onBackPressed() {


        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return

        } else {

            showHome()
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)

    }



    //this method will handle the onclick listeners of items in app drawer
    override fun onClick(v: View?) {

        var fragmentClass: Class<*>? = null

        if (v?.getId() == R.id.text1) {

            fragmentClass = MyMasjidFragment::class.java
        }

        else if (v?.getId() == R.id.text2) {
            //do something here if button3 is clicked
            fragmentClass = FavouritesFragment::class.java
        }

        else if (v?.getId() == R.id.text3) {
           // fragmentClass = MasajidListFragment::class.java
        }

        else if(v?.id == R.id.text4){

          //  fragmentClass = NearestMasjid::class.java

        }

        else if (v?.getId() == R.id.text5) {

            fragmentClass = NearestJummah::class.java
        }

        else if (v?.getId() == R.id.text6) {
            fragmentClass = Fragment_Qibla_Direction::class.java
        }



        else if (v?.getId() == R.id.text7) {
            fragmentClass = FragmentNotification::class.java
        }



        else if (v?.getId() == R.id.text8) {
            fragmentClass = FragmentAddMosque::class.java
        }



        else if (v?.getId() == R.id.text9) {
            fragmentClass = DuaFragment::class.java
        }



        else if (v?.getId() == R.id.text10) {
            fragmentClass = FragmentSettings::class.java
        }

        else if (v?.getId() == R.id.text11) {
            fragmentClass = FeedbackFragment::class.java
        }






        if (fragment != null) {

            val manager = supportFragmentManager
            manager.beginTransaction().replace(R.id.Screen_Area, fragment!!, fragment!!.getTag()).commit()
        }


        try {
            fragment = fragmentClass?.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //this will replace current fragment with the fragment requested on click listener of drawer items
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.Screen_Area, fragment!!).commit()

        //closes the drawer
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)

    }



    //this method show home fragment
    private fun showHome() {

        fragment = HomeFragment()
        if (fragment != null) {

            val manager = supportFragmentManager
            manager.beginTransaction().replace(R.id.Screen_Area, fragment!!, fragment!!.tag).commit()
        }
    }



    //this method will show home fragment on activity creation
    private fun showHomeOnFirstTime(){

        val myf = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.Screen_Area, myf)
        transaction.commit()
    }

}



