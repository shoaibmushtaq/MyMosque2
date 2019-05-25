package com.centosquare.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.centosquare.R
import android.content.SharedPreferences
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.content.Intent
import android.widget.Toast
import com.centosquare.NavigationDrawerActivity
import android.content.DialogInterface
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import java.lang.Exception
import android.telephony.TelephonyManager
import retrofit2.Retrofit
import com.centosquare.API.ApiInterface
import com.centosquare.API.ApiClient
import com.centosquare.Models.PrimaryMosqueData
import com.centosquare.Models.UserId
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices.FusedLocationApi
import com.google.android.gms.tasks.OnSuccessListener


class PermissionActivity : AppCompatActivity() {

    //declaring location provider client
    private var mFusedLocationClient: FusedLocationProviderClient? = null


    //declaring emi number varriable
    var emiNumber = ""

    //declaring api interface instance
    var apiInterface: ApiInterface? = null

    //Declaring permission tag and permission array in which every neeeded permission is stored
    private var PERMISSION_REQUEST_CODE = 1240
    private var PERMISSIONS = arrayOf(

        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    //declaring shared preference related varriables
    var sharedPreference: SharedPreferences? = null
    var userId: Int = 0
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.centosquare.R.layout.activity_permission)

        //intializing fused location cient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //intializing shared preference to store user details and set user id to 0 from shared preference
        sharedPreference = getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        userId = sharedPreference!!.getInt("ID", 0)

        //declaring and initializing retrofit
        val apiClient = ApiClient()
        apiInterface = apiClient.getApiClient()!!.create(ApiInterface::class.java)




        //intializing button
        val btnPermission: Button = findViewById(R.id.permissionBtn)
        btnPermission.setOnClickListener(View.OnClickListener {

            //start main activity on click listener
            startActivity(Intent(this, NavigationDrawerActivity::class.java))
        })


        //this code will check if the user has given permission or not previously
        if (hasPermissions(this, PERMISSIONS)) {

            if (userId == 0) {

                //calling methods to get emi number and location
                myTelephoneManager()
                getLastLocation()
            } else {

                try {

                    //calling methods to get primary mosque id and location
                    getPrimaryMosqueId(userId)
                    getLastLocation()

                } catch (ex: Exception) {

                    ex.printStackTrace()
                }
            }



        }

        //if the user has not given permissions previously then this method will be called
        else {
            //calling function
            requestStoragePermission()
        }


    }
    //end of oncreate method


    //this method will ask user for necessory permissions
    private fun requestStoragePermission() {
        //if the user has denied all permissions then an custom alert box will be displayed telling user that permissions are required to run application
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE) &&
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) &&
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("permissions are required otherwise app will not work ")
                .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ), PERMISSION_REQUEST_CODE
                    )
                })
                .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()
                })
                .create().show()
        }


        //if the user has not given permission before then this code will ask user for permission and if user has not given a particular permission then it will ask user for that permission
        else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                , PERMISSION_REQUEST_CODE
            )
        }
    }
    //end of request storage permissions method


    //this method will check if the required permissions are already given or not if they are given then it will return true otherwise it will return false
    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean {

        if (context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }

        return true
    }

    //end of has permissions method


    //this method is executed when the user will give any permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        //verifying the request code
        if (requestCode == PERMISSION_REQUEST_CODE) {

            //checking if the permission array is empty or not
            if (grantResults.size > 0) {

                //checking if the oth index permission and 1st index permission are given or not
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {


                    //if all permissions are given then this code wil run
                    myTelephoneManager()
                    getLastLocation()
                    Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show()


                }

                //otherwise this code will run
                else {
                    Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }
    }

    //end of onrequestpermissionresultmethod


    //this method return the imei number
    @SuppressWarnings("deprecation")
    @SuppressLint("MissingPermission")
    private fun myTelephoneManager() {

        val telecomManager = (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager)!!


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                emiNumber = telecomManager.imei
            } else {
                emiNumber = telecomManager.deviceId
            }
        }

//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            emiNumber = telecomManager.imei
//        }

        Toast.makeText(this, "Get Your EMI Number:" + emiNumber + "For Making You User ", Toast.LENGTH_SHORT).show()

        //calling method to set imei number in to server
        setEmiNumberIntoServer()

    }


    //this method will accept user id and return primary mosque of the user
    private fun getPrimaryMosqueId(id: Int) {

        val call = apiInterface!!.getPrimary(id)

        call.enqueue(object : Callback<PrimaryMosqueData> {
            override fun onResponse(call: Call<PrimaryMosqueData>, response: Response<PrimaryMosqueData>) {
                if (response.code() == 200) {


                    try {

                        Log.d("testing", "" + response.code())



                        //if response is not null then populte shared preference of primary mosque data
                        val editor: SharedPreferences.Editor = applicationContext.getSharedPreferences("GetPrimaryMosque", MODE_PRIVATE).edit()
                        editor.putInt("PM_ID", response.body()!!.ID)
                        editor.putString("PM_NAME", response.body()!!.name)
                        editor.putString("PM_URL", response.body()!!.imageurl)
                        editor.putString("PM_Address", response.body()!!.address)
                        editor.putString("PM_Milesaway", "")
                        editor.putString("PM_longitude", response.body()!!.longtitude)
                        editor.putString("PM_latitude", response.body()!!.latitude)
                        editor.putString("PM_TopicsName", response.body()!!.topics_name)
                        editor.apply()

                        Toast.makeText(applicationContext, " Primary Mosque name is : " + response.body()!!.name, Toast.LENGTH_LONG).show()


                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<PrimaryMosqueData>, t: Throwable) {

            }
        })


    }

    // end of get primary mosque id method


    //this method will save imei number on server and create user and return user id
    private fun setEmiNumberIntoServer() {

        val call = apiInterface!!.GetUserID(emiNumber)

        call.enqueue(object : Callback<UserId> {
            override fun onResponse(call: Call<UserId>, response: Response<UserId>) {
                if (response.code() == 200) {


                    try {

                        Log.d("testEmi", "" + response.code())

                        //if response is successful then save the user id in id varriable
                        id = response.body()!!.user_id

                        val editor: SharedPreferences.Editor = this@PermissionActivity.getSharedPreferences("USER_PREFERENCE", MODE_PRIVATE).edit()
                        editor.putInt("ID", id)
                        editor.putString("emi", response.body()!!.emi_number)
                        editor.apply()

                        Log.d("testEmiId", "" + id)

                        Toast.makeText(applicationContext, "User  ID:" + response.body()!!.emi_number,Toast.LENGTH_LONG).show()

                        //and pass the user id in getPrimaryMosque method
                        getPrimaryMosqueId(id)


                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<UserId>, t: Throwable) {

            }
        })


    }

    //end of setEmiIntoServer method


         private fun getLastLocation() {

             if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                 mFusedLocationClient!!.lastLocation
                     .addOnCompleteListener(this) { task ->
                         if (task.isSuccessful && task.result != null) {
                             var mLastLocation = task.result

                             Log.d("longitude", "" + mLastLocation.longitude)
                             Log.d("latitude", "" + mLastLocation.latitude)

                             var editor = getSharedPreferences("LatLang", Context.MODE_PRIVATE).edit()
                             editor.putString("Lat", mLastLocation.latitude.toString())
                             editor.putString("Lag", mLastLocation.longitude.toString())
                             editor.apply()

                         } else {
                             Log.d("it", "getLastLocation:exception", task.exception)
                         }
                     }
             }
         }
    // end of getLastLocation method



}






































