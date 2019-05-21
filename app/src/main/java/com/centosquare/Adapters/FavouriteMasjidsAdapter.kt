package com.centosquare.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.centosquare.R
import java.util.ArrayList

class FavouriteMasjidsAdapter(val context: Context, val masjidNames: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.centosquare.Adapters.ViewHolder {

        //return view to viewholder class
        val view = LayoutInflater.from(context).inflate(R.layout.favourites_item, parent, false)
        return com.centosquare.Adapters.ViewHolder(view)


    }

    override fun onBindViewHolder(holder: com.centosquare.Adapters.ViewHolder, position: Int) {

        holder.masjidName.setText(masjidNames[position])

        //set the masjid name to viewholder textview item

    }


    override fun getItemCount(): Int {

        //return size of given arrayist
        return masjidNames.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var masajidName: TextView? = null

        init {


            //getting the reference of masjid name textview from layout xml file
            masajidName = itemView.findViewById(R.id.txt_Masajid)


        }
    }




}



