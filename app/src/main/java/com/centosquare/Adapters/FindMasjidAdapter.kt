package com.centosquare.Adapters

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.centosquare.Fragments.ProfileFragment
import com.centosquare.Models.MasjidModel
import com.centosquare.R
import kotlinx.android.synthetic.main.find_masjid_item_list.view.*



public class FindMasjidAdapter(var masjidNames : ArrayList<MasjidModel> , val context : Context) : Adapter<ViewHolder>(), View.OnClickListener {



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        //return view to viewholder class
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.find_masjid_item_list, p0, false))


    }

    override fun getItemCount(): Int {

        //return size of given arrayist
        return masjidNames.size
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {

        //set the masjid name to viewholder textview item and set onclick listener to the whole view
        p0.masjidName.text = masjidNames.get(position).name
        p0.itemView.setOnClickListener(this)


    }


    override fun onClick(p0: View?) {

        //replace the fragment with Profile fragment
        val activity = p0?.getContext() as AppCompatActivity
        activity.supportFragmentManager.beginTransaction().replace(R.id.Screen_Area, ProfileFragment()).commit()


    }


    public fun addData(list: ArrayList<MasjidModel>) {

        for (im in list) {

            masjidNames.add(im)


        }
        notifyDataSetChanged()

    }
}

class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView)
{

    //getting the reference of masjid name textview from layout xml file
    val masjidName = itemView.txt_Masajid
}











