package com.centosquare

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList


class FragmentThird : Fragment() {

    private val JamaatTimes = ArrayList<String>()
    internal var v: View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_third, container, false)
        v?.findViewById<View>(R.id.thirdfrag)
        reclerviewInIt()
        return v
    }


    fun reclerviewInIt() {

        val recyclerview = v?.findViewById<RecyclerView>(R.id.RV_jamaat_times)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview?.layoutManager = linearLayoutManager
        val adapterRVJamaatTimes = AdapterRVJamaatTimes(this!!.activity!!, JamaatTimes)
        recyclerview?.adapter = adapterRVJamaatTimes
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")
        JamaatTimes.add("05:45")


    }


}
