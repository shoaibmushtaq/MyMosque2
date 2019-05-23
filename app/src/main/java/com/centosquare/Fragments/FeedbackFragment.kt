package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.centosquare.R

class FeedbackFragment : Fragment() {

    val feedbackView = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        //<For Toolbar>
        val toolbar = activity!!.findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.VISIBLE
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        mTitle.text = "FeedBack"
        //</For Toolbar>


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }


}
