package com.centosquare.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import com.centosquare.Adapters.FindMasjidAdapter
import com.centosquare.NavigationDrawerActivity
import com.centosquare.R
import java.util.ArrayList
import com.centosquare.Models.MetaModel
import com.centosquare.Models.MasjidModel
import com.centosquare.API.ApiInterface
import com.centosquare.Models.MasjidArrayList
import com.centosquare.API.ApiClient
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.widget.AbsListView
import android.widget.Toast
import com.centosquare.Models.Feedback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.centosquare.Models.LinksModel




class HomeFragment : Fragment() {

    private var isScrolling = true
    private var pastVisibleItems: Int = 0
    var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var previous_total : Int = 0
    private var view_threeshold = 10
    private var mosqueData: ArrayList<MasjidModel>? = null
    private var metaModel: MetaModel? = null
    private var pageNo: Int = 1
    private var info: MasjidArrayList? = null
    private var apiInterface: ApiInterface? = null
    var recyclerView : RecyclerView? = null
    var layoutManager : LinearLayoutManager? = null
    var userId : Int? = null
    var  adapter : FindMasjidAdapter? = null


    var homeView: View? = null
     var humbburger: ImageView? = null
     var search_masjid: EditText? = null
     var mDrawerLayout: DrawerLayout? = null
     var masajidNames = ArrayList<String>()

    private val lastVisibleItemPosition: Int
        get() = layoutManager!!.findLastVisibleItemPosition()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)

        val apiClient = ApiClient()
        apiInterface = apiClient.getApiClient()!!.create(ApiInterface::class.java)

        //<For Toolbar>
        val toolbar = activity!!.findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.GONE
        //</For Toolbar

        val UserPerfs = activity!!.getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        userId = UserPerfs.getInt("ID", 0)
        val call = apiInterface!!.getInfo(userId!!,pageNo)


        call.enqueue(object : Callback<MasjidArrayList> {
            override fun onResponse(call: Call<MasjidArrayList>, response: Response<MasjidArrayList>) {

                info = response.body()

                mosqueData = response.body()!!.masjidModelArrayList
                val linksModel = response.body()!!.linksModel
                metaModel = response.body()!!.metaModel


                recyclerView = homeView?.findViewById(R.id.RV_masajidList) as RecyclerView
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerView?.layoutManager = layoutManager
                adapter = FindMasjidAdapter(mosqueData!!, activity as NavigationDrawerActivity)
                recyclerView?.adapter = adapter


                Toast.makeText(activity,"First Page is loaded" ,Toast.LENGTH_SHORT).show()




            }


            override fun onFailure(call: Call<MasjidArrayList>, t: Throwable) {





            }
        })



        //calling the method to set recycler view
      //  RecyclerViewMasajid()

        search_masjid = homeView?.findViewById(R.id.edit_txt_masjid)
        humbburger = homeView?.findViewById(R.id.humburgerIcon)
        mDrawerLayout = activity!!.findViewById(R.id.drawer_layout) as DrawerLayout
        humbburger?.setOnClickListener(View.OnClickListener {

            mDrawerLayout?.openDrawer(Gravity.RIGHT)

        })

        setRecyclerViewScrollListener()

        //this will handle the on touch listener of edit text
        search_masjid?.setOnTouchListener(View.OnTouchListener { v, event ->
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            false
        })









        return  homeView
    }


    private fun setRecyclerViewScrollListener() {
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)

                Toast.makeText(activity ,"inside onscrolled",Toast.LENGTH_SHORT).show()
                visibleItemCount = layoutManager!!.childCount
                totalItemCount = layoutManager!!.itemCount
                pastVisibleItems = layoutManager!!.findFirstVisibleItemPosition()

                if (dy > 0)
                {
                    if (isScrolling){

                        if (totalItemCount > previous_total){

                            isScrolling = false

                            previous_total = totalItemCount
                        }
                    }

                    if (!isScrolling && (totalItemCount - visibleItemCount)<= (pastVisibleItems + mosqueData!!.size)){

                        pageNo++
                        fetch_data()
                        isScrolling = true
                    }
                }



            }

            })
        }




    //this method will add masjid names to the list and set recycler adapter
//    fun RecyclerViewMasajid() {
//        masajidNames.add("Masjid-e-Nagina")
//        masajidNames.add("Masjid-e-Aqsa")
//        masajidNames.add("Masjid-e-Iqra")
//        masajidNames.add("Masjid-e-Tooba")
//        masajidNames.add("Masjid-e-Ayesha")
//        masajidNames.add("Masjid-e-Nabvi")
//
//
//        val recyclerView = homeView?.findViewById(R.id.RV_masajidList) as RecyclerView
//        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        recyclerView.layoutManager = layoutManager
//        val adapter = FindMasjidAdapter(masajidNames, activity as NavigationDrawerActivity)
//        recyclerView.adapter = adapter
//
//    }



    fun fetch_data() {


                     var secondPageCall = apiInterface!!.getInfo(userId!!,pageNo)

                    secondPageCall.enqueue(object : Callback<MasjidArrayList> {
                        override fun onResponse(call: Call<MasjidArrayList>, response: Response<MasjidArrayList>) {

                            var masjidArrayList : MasjidArrayList? = response.body()
                            var seconPageData = response.body()!!.masjidModelArrayList

                      val SecondPageLinksModel = response.body()!!.linksModel
                        metaModel = response.body()!!.metaModel

                            adapter?.addData(seconPageData)

                       }


                       override fun onFailure(call: Call<MasjidArrayList>, t: Throwable) {



                        }
                    })

                }






}
