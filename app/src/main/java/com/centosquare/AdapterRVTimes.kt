package com.centosquare

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.centosquare.AdapterRVTimes.ViewHolder
import java.util.ArrayList

class AdapterRVTimes(private val mContext: Context, Names: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
    private val TAG = "RecyclerViewAdapter_F_ADS"

    private var NamazTimes = ArrayList<String>()

    private var context: Context?=null
    init {
this.context=mContext
        this.NamazTimes=Names

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.namz_times_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.Time.setText(NamazTimes[position])


    }

    override fun getItemCount(): Int {
        return NamazTimes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var Time: TextView


        init {


            Time = itemView.findViewById(R.id.txt_isha_time_beginning)


        }
    }



}