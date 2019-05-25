package com.centosquare.Models

import com.google.gson.annotations.SerializedName

data class PrimaryMosqueData(

        @SerializedName("ID")
        var  ID : Int,

       @SerializedName("name")
       var  name : String,

        @SerializedName("longtitude")
        var   longtitude : String,

        @SerializedName("latitude")
        var  latitude : String,

        @SerializedName("imageurl")
        var  imageurl:String,

        @SerializedName("address")
        var  address:String,

        @SerializedName("farvoriate")
        var  farvoriate:Int,

        @SerializedName("farvoriate_id")
        var  farvoriate_id : Int,

        @SerializedName("topics_name")
        var topics_name : String
)