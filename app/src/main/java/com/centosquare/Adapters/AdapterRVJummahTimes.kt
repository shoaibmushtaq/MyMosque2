package com.centosquare

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.centosquare.AdapterRVJummahTimes.ViewHolder
import java.util.ArrayList

class AdapterRVJummahTimes(private val mContext: Context, Names: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
    private var JummahTimes = ArrayList<String>()
    private val TAG = "RecyclerViewAdapter_F_ADS"

    var context :Context?=null
init{
    this.context=mContext
    this.JummahTimes=Names

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.jummah_times_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.Time.setText(JummahTimes[position])


    }

    override fun getItemCount(): Int {
        return JummahTimes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var Time: TextView


        init {


            Time = itemView.findViewById(R.id.txt_1st_jummah)


        }
    }


}



