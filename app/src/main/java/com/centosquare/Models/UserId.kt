package com.centosquare.Models

import com.google.gson.annotations.SerializedName

data class UserId(
    @SerializedName("emi_number")
    var emi_number : String,

    @SerializedName("u_id")
    var  user_id : Int

) {




}

