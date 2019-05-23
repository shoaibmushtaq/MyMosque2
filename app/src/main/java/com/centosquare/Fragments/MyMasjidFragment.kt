package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.centosquare.FragmentPrayerTimes

import com.centosquare.R

class MyMasjidFragment : Fragment() {

    var myMasjidView : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        myMasjidView = inflater.inflate(R.layout.fragment_my_masjid, container, false)


        //<For Toolbar>
        val toolbar = activity!!.findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.VISIBLE
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        mTitle.text = "My Mosque"
        //</For Toolbar>

        //getting the reference of ask imam button and set the onclick listener
        val askImamBtn = myMasjidView?.findViewById<Button>(R.id.askImamBTN)
        askImamBtn?.setOnClickListener(View.OnClickListener {

            //replace the current gragment with ask imam fragment
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.Screen_Area, AskImamFragment()).commit()


        })

        //setting on click listener to prayer times button and open prayer times fragment
        val prayerTimeBtn = myMasjidView?.findViewById<Button>(R.id.MosqueBTN)

        prayerTimeBtn?.setOnClickListener(View.OnClickListener {


            //replace the current gragment with Prayer times fragment
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.Screen_Area, FragmentPrayerTimes()).commit()

        })




        return  myMasjidView
    }


}
