package com.example.cardview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardview.R
import com.example.cardview.adapter.RecyclerViewAdapter
import com.example.cardview.model.FootballModel
import com.example.cardview.service.FootballAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {



    private val FIRST_URL="https://raw.githubusercontent.com/"
    private var footballModels:ArrayList<FootballModel>?=null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  https://raw.githubusercontent.com/baranselklnc/fakejson/master/fakeapiFootball.json

   val recyclerView:RecyclerView=findViewById(R.id.recylerView)
        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        loadData()


    }
    private fun loadData(){

        val retrofit=Retrofit.Builder()
            .baseUrl(FIRST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service=retrofit.create(FootballAPI::class.java)
        val call=service.getData()
        call.enqueue(object:Callback<List<FootballModel>>{
            override fun onResponse(
                call: Call<List<FootballModel>>,
                response: Response<List<FootballModel>>
            ) {
                if (response.isSuccessful){

                    response.body()?.let  {it->
                    footballModels= ArrayList(it)
                    footballModels?.let {
                        recyclerViewAdapter=RecyclerViewAdapter(it,this@MainActivity)
                        val recyclerView:RecyclerView=findViewById(R.id.recylerView)
                        recyclerView.adapter=recyclerViewAdapter
                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<FootballModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }


}
