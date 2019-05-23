package com.centosquare

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.centosquare.Fragments.FragmentFirst
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator


class FragmentPrayerTimes : Fragment() {
    internal var v: View?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }//end of onCreate method

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_prayer_times, container, false)

        //<For Toolbar>
        val toolbar = activity?.findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar?
        toolbar?.visibility = View.VISIBLE
        val mTitle = toolbar?.findViewById<View>(R.id.toolbar_title) as TextView?
        mTitle?.text = "Prayer Times"
        //</For Toolbar>


        val pager = v?.findViewById<View>(R.id.viewPager) as ViewPager
        val leftNav = v?.findViewById<View>(R.id.left_nav) as ImageView
        val rightNav = v?.findViewById<View>(R.id.right_nav) as ImageView
        // Images left navigation


        pager.adapter = MyPagerAdapter(this!!.fragmentManager!!)
        val viewPagerIndicator = v?.findViewById<View>(R.id.view_pager_indicator) as ViewPagerIndicator

        viewPagerIndicator.setupWithViewPager(pager)

        leftNav.setOnClickListener {
            var tab = pager.currentItem
            if (tab > 0) {
                tab--
                pager.currentItem = tab
            } else if (tab == 0) {
                pager.currentItem = tab
            }
        }

        // Images right navigatin
        rightNav.setOnClickListener {
            var tab = pager.currentItem
            tab++
            pager.currentItem = tab
        }















        return v
    }//End onCreateView Method


    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(pos: Int): Fragment {
            when (pos) {

                0 -> return FragmentFirst()
                1 -> return FragmentSecond()
                2 -> return FragmentThird()

                3 -> return FragmentFourth()

                else -> return FragmentFourth()
            }
        }

        override fun getCount(): Int {
            return 4
        }
    }


}
