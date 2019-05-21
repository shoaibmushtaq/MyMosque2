package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.centosquare.Adapters.FavouriteMasjidsAdapter
import com.centosquare.NavigationDrawerActivity

import com.centosquare.R
import java.util.ArrayList

class FavouritesFragment : Fragment() {

    private var favouritesView: View? =null
    private val favouritesMasjids = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        favouritesView = inflater.inflate(R.layout.fragment_favourites, container, false)

        //<For Toolbar>
        val toolbar = activity!!.findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.VISIBLE
        val backbutton = toolbar.findViewById<View>(R.id.backButton) as ImageView
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        mTitle.text = "Favorite"
        //</For Toolbar>


        //calling method to add recycler adapter
        RecyclerViewMasajid()

        return favouritesView

    }

    //this method adds values to the list and set recycler view adapter
    fun RecyclerViewMasajid() {
        favouritesMasjids.add("Masjid-e-Nagina")
        favouritesMasjids.add("Masjid-e-Aqsa")
        favouritesMasjids.add("Masjid-e-Iqra")
        favouritesMasjids.add("Masjid-e-Tooba")
        favouritesMasjids.add("Masjid-e-Ayesha")
        favouritesMasjids.add("Masjid-e-Nabvi")


        val recyclerView = favouritesView?.findViewById(R.id.favourites_list) as RecyclerView
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = FavouriteMasjidsAdapter(activity as NavigationDrawerActivity , favouritesMasjids)
        recyclerView.adapter = adapter


    }


}
