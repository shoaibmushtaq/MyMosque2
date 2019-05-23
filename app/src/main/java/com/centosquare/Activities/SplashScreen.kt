package com.centosquare.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.centosquare.NavigationDrawerActivity
import com.centosquare.OnBoarding
import com.centosquare.R

class SplashScreen : AppCompatActivity() {


    //initializing time varriable for splash screen
    private val SPLASH_SCREEN_TIME:Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            //if user is already logged in openeing the profile activity
            val mainIntent = Intent(this@SplashScreen, OnBoarding::class.java)
            this@SplashScreen.startActivity(mainIntent)
            this@SplashScreen.finish()
        }, SPLASH_SCREEN_TIME)






    }

}