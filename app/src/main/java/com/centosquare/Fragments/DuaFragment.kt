package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.centosquare.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DuaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        var view:View =   inflater.inflate(R.layout.fragment_dua , container , false)

        //<For Toolbar>
        val toolbar = activity!!.findViewById<Toolbar>(R.id.toolbar)
        toolbar.visibility=View.VISIBLE

        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)
        title.text = "Duas"
        //</For Toolbar>


        return view
    }


}
