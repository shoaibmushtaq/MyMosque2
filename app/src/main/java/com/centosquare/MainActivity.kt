package com.centosquare

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = FragmentAddMosque()
        val transaction = supportFragmentManager.beginTransaction()
         transaction.replace(R.id.fragmentlayout,fragment)
        transaction.commit()
    }
}
