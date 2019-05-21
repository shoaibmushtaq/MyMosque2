package com.centosquare

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.centosquare.AdapterRVJamaatTimes.ViewHolder
import java.util.ArrayList

class AdapterRVJamaatTimes(private val context: Context, Names: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>(){

    private var JamaatTimes = ArrayList<String>()
    private var mContext: Context? = null
    private val TAG = "RecyclerViewAdapter_F_ADS"
// INITIALIZING CONTEXT AND ARRAYLIST
 init{
        this.mContext = context
        this.JamaatTimes = Names

    }

    override fun getItemCount(): Int {
        return JamaatTimes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Time.setText(JamaatTimes[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jamaat_times_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var Time: TextView


        init {


            Time = itemView.findViewById(R.id.txt_isha_time_beginning)


        }
    }


}