package com.centosquare.Fragments


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.centosquare.R
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.centosquare.API.ApiClient
import com.centosquare.API.ApiInterface
import com.centosquare.Models.Feedback
import com.centosquare.Models.FeedbackMessage
import com.centosquare.Models.PrimaryMosqueData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.Exception


class FeedbackFragment : Fragment() {

    var feedbackView : View? = null
    var feedbackText : EditText? = null
    var feedbackName : EditText? = null
    var feedbackContact : EditText? = null

    var message : String =""
    var name : String = ""
    var contact : String = ""
    var userId : Int=0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        feedbackView=inflater.inflate(R.layout.fragment_feedback,container,false)
        //<For Toolbar>
        val toolbar = activity!!.findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        toolbar.visibility = View.VISIBLE
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        mTitle.text = "FeedBack"
        //</For Toolbar>


        val UserPerfs = activity!!.getSharedPreferences("USER_PREFERENCE", MODE_PRIVATE)
        userId = UserPerfs.getInt("ID", 0)

        feedbackText = feedbackView?.findViewById(R.id.feedback_text)
        feedbackName = feedbackView?.findViewById(R.id.feedback_name)
        feedbackContact = feedbackView?.findViewById(R.id.feedback_contact)

        message = feedbackText?.text.toString()
        name = feedbackName?.text.toString()
        contact = feedbackContact?.text.toString()

        val submit = feedbackView?.findViewById<Button>(R.id.btn_submit)

        submit?.setOnClickListener(View.OnClickListener {

            sendFeedback(userId,feedbackText?.text.toString().trim(),feedbackContact?.text.toString().trim(),feedbackName?.text.toString().trim())

            Log.d("my_u_id",""+userId)


        })











        // Inflate the layout for this fragment

        return feedbackView
    }


//    fun checkValidation(){
//
//        if (message!!.isBlank()){
//
//            feedbackText?.error = "fill this"
//            feedbackText?.requestFocus()
//            return
//        }
//
//        if (name!!.isBlank()){
//
//            feedbackName?.error = "fill this"
//            feedbackName?.requestFocus()
//            return
//        }
//
//        if (contact!!.isBlank()){
//
//            feedbackContact?.error = "fill this"
//            feedbackContact?.requestFocus()
//            return
//        }
//
//        sendFeedback(userId,message,contact,name)
//        Toast.makeText(activity,"Your Mosque Request Sent to the Admin", Toast.LENGTH_SHORT).show();
//        feedbackText?.setText("")
//        feedbackName?.setText("")
//        feedbackContact?.setText("")
//    }

    fun sendFeedback(id : Int,message : String, contact :String , name : String){

        val apiClient = ApiClient()

        val apiInterface : ApiInterface =apiClient.getApiClient()!!.create(ApiInterface::class.java)

        val call = apiInterface.feedback(id,message,contact,name)


        call.enqueue(object : Callback<Feedback> {
            override fun onResponse(call: Call<Feedback>, response: Response<Feedback>) {

                Toast.makeText(activity,"Your Feedback is "+response.body()!!.message,Toast.LENGTH_LONG).show()
                feedbackText?.setText("")
                feedbackName?.setText("")
                feedbackContact?.setText("")

            }

            override fun onFailure(call: Call<Feedback>, t: Throwable) {

                Toast.makeText(activity,"operation failed",Toast.LENGTH_LONG).show()


            }
        })
    }
}








