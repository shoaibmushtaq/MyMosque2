package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import com.centosquare.Adapters.FindMasjidAdapter
import com.centosquare.NavigationDrawerActivity

import com.centosquare.R
import java.util.ArrayList


class HomeFragment : Fragment() {


     var homeView: View? = null
     var humbburger: ImageView? = null
     var search_masjid: EditText? = null
     var mDrawerLayout: DrawerLayout? = null
     var masajidNames = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)

        //<For Toolbar>
        val toolbar = activity!!.findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.GONE
        //</For Toolbar


        //calling the method to set recycler view
        RecyclerViewMasajid()

        search_masjid = homeView?.findViewById(R.id.edit_txt_masjid)
        humbburger = homeView?.findViewById(R.id.humburgerIcon)
        mDrawerLayout = activity!!.findViewById(R.id.drawer_layout) as DrawerLayout
        humbburger?.setOnClickListener(View.OnClickListener {

            mDrawerLayout?.openDrawer(Gravity.RIGHT)

        })

        //this will handle the on touch listener of edit text
        search_masjid?.setOnTouchListener(View.OnTouchListener { v, event ->
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            false
        })


        return  homeView
    }


    //this method will add masjid names to the list and set recycler adapter
    fun RecyclerViewMasajid() {
        masajidNames.add("Masjid-e-Nagina")
        masajidNames.add("Masjid-e-Aqsa")
        masajidNames.add("Masjid-e-Iqra")
        masajidNames.add("Masjid-e-Tooba")
        masajidNames.add("Masjid-e-Ayesha")
        masajidNames.add("Masjid-e-Nabvi")


        val recyclerView = homeView?.findViewById(R.id.RV_masajidList) as RecyclerView
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = FindMasjidAdapter(masajidNames, activity as NavigationDrawerActivity)
        recyclerView.adapter = adapter

    }
}
