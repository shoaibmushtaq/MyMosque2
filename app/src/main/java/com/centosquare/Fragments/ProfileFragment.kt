package com.centosquare.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import com.centosquare.NavigationDrawerActivity
import com.centosquare.R

class ProfileFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view:View =   inflater.inflate(R.layout.fragment_profile , container , false)


        //getting the reference of mosque button and set on click listener
        val btnMosque = view.findViewById<Button>(R.id.MosqueBTN)
        btnMosque.setOnClickListener(View.OnClickListener {

            //relace the current fragment with my masjid fragment
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.Screen_Area, MyMasjidFragment()).commit()

        })


        //<For Toolbar>
        val toolBar = activity!!.findViewById<Toolbar>(R.id.toolbar) as android.support.v7.widget.Toolbar?

        toolBar?.visibility = View.VISIBLE
        val title = toolBar?.findViewById<TextView>(R.id.toolbar_title)
        title?.text = "Profile"
        //</For Toolbar>



        return view


    }


}
