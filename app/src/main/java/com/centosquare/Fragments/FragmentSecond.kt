package com.centosquare

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

//THESE FRAGMENTS FROM 2 TO 4 ARE SHOWING THE JAMAAT TIMES
class FragmentSecond : Fragment() {
    private val NamazTimes = ArrayList<String>()
    internal var v: View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_second, container, false)

        v?.findViewById<View>(R.id.secondfrag)
        reclerviewInIt()
        return v
    }


    fun reclerviewInIt() {

        val recyclerview = v?.findViewById<RecyclerView>(R.id.RV_times)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview?.layoutManager = linearLayoutManager
        val adapterRVTimes = AdapterRVTimes(this!!.activity!!, NamazTimes)
        recyclerview?.adapter = adapterRVTimes
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")
        NamazTimes.add("05:45")


    }


}
