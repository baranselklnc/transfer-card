package com.example.cardview.service

import com.example.cardview.model.FootballModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface FootballAPI {
    @GET("baranselklnc/fakejson/master/fakeapiFootball.json")
   fun getData(): Observable<List<FootballModel>>
   // fun getData():Call<List<FootballModel>>
}