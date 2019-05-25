package com.centosquare.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    val BASE_URL = "http://masjidi.co.uk/api/"
    var retrofit: Retrofit? = null

     fun getApiClient(): Retrofit? {

        if (retrofit == null) {

            retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofit
    }



}