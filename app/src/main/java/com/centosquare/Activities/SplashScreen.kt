package com.centosquare.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.centosquare.NavigationDrawerActivity
import com.centosquare.R

class SplashScreen : AppCompatActivity() {


    //initializing time varriable for splash screen
    private val SPLASH_SCREEN_TIME:Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)




        Handler().postDelayed({

             //this code will delay the splashscreen activity for 4 second and then send user to main activity
             val intent = Intent(this, NavigationDrawerActivity::class.java)
             startActivity(intent)
             finish()
             }, SPLASH_SCREEN_TIME)

    }

}