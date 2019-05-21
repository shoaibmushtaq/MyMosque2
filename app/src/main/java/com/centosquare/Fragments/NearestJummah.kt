package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.centosquare.R

class NearestJummah : Fragment() {

   private var nearestJummahView : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        nearestJummahView = inflater.inflate(R.layout.fragment_nearest_jummah, container, false)

        //For Toolbar
        val toolbar = activity!!.findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.VISIBLE
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        mTitle.text = "Nearest Jummah"
        //For Toolbar


        return nearestJummahView
    }


}
