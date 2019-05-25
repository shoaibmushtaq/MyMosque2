package com.centosquare.API

import com.centosquare.Models.Feedback
import com.centosquare.Models.FeedbackMessage
import com.centosquare.Models.PrimaryMosqueData
import com.centosquare.Models.UserId
import retrofit2.Call
import com.centosquare.Models.MasjidArrayList
import retrofit2.http.*


public interface ApiInterface {

    //Create User Function Call
    @FormUrlEncoded
    @POST("createuser")
    fun GetUserID(@Field("emi") emi: String): Call<UserId>



    //SetFavouriteFunction call
    @FormUrlEncoded
    @POST("getprimarymosque")
    fun getPrimary(@Field("userid") u_id: Int): Call<PrimaryMosqueData>


    @FormUrlEncoded
    @POST("feedback/{u_id}")
    fun feedback(@Path("u_id") id : Int,
                 @Field("msg") message : String,
                 @Field("contact") contact : String,
                 @Field("name") name : String
                 ) : Call<Feedback>


    @GET("getMosquesList/{u_id}")
    fun getInfo(@Path("u_id") id : Int,
        @Query("page") page: Int): Call<MasjidArrayList>



}
