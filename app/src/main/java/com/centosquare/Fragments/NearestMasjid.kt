package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.centosquare.Adapters.FindMasjidAdapter
import com.centosquare.NavigationDrawerActivity

import com.centosquare.R
import java.util.ArrayList

class NearestMasjid : Fragment() {

    private var nearestMasjidView: View? = null

    private val masajidName = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        nearestMasjidView = inflater.inflate(R.layout.fragment_nearest_masjid, container, false)

        //<For Toolbar>
        val toolbar = activity!!.findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.VISIBLE
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        mTitle.text = "Nearest Masajid"
        //</For Toolbar


        //calling the method to set recycler view
        RecyclerViewMasajid()



        return nearestMasjidView
    }


    //this method will add masjid names to the list and set recycler adapter
   private fun RecyclerViewMasajid() {


        masajidName.add("Masjid-e-Nagina")
        masajidName.add("Masjid-e-Aqsa")
        masajidName.add("Masjid-e-Iqra")
        masajidName.add("Masjid-e-Tooba")
        masajidName.add("Masjid-e-Ayesha")
        masajidName.add("Masjid-e-Nabvi")


        val recyclerView = nearestMasjidView?.findViewById(R.id.nearest_masajid_List) as RecyclerView
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = FindMasjidAdapter(masajidName, activity as NavigationDrawerActivity )
        recyclerView.adapter = adapter


    }


}
