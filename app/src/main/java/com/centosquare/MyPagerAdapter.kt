package com.centosquare

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyPagerAdapter(private var layouts: IntArray, private var context: Context) : PagerAdapter() {
init {
    this.layouts = layouts
    this.context = context
}

    private var inflater: LayoutInflater? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


    override fun getCount(): Int {
        return layouts.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater!!.inflate(layouts[position], container, false)
        container.addView(v)
        return v
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val v = `object` as View
        container.removeView(v)
    }


}