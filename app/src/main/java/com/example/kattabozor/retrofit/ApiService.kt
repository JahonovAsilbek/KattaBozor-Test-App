package com.example.kattabozor.retrofit

import com.example.kattabozor.entity.Offers
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("offers")
    fun getWeather(): Call<Offers>
}