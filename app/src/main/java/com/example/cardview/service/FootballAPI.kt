package com.example.cardview.service

import com.example.cardview.model.FootballModel
import retrofit2.Call
import retrofit2.http.GET

interface FootballAPI {
    @GET("baranselklnc/fakejson/master/fakeapiFootball.json")

    fun getData():Call<List<FootballModel>>
}