package com.example.cardview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardview.R
import com.example.cardview.adapter.RecyclerViewAdapter
import com.example.cardview.model.FootballModel
import com.example.cardview.service.FootballAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private var  compositeDisposable: CompositeDisposable?=null
    private val FIRST_URL="https://raw.githubusercontent.com/"
    private var footballModels:ArrayList<FootballModel>?=null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  https://raw.githubusercontent.com/baranselklnc/fakejson/master/fakeapiFootball.json
        compositeDisposable = CompositeDisposable()

   val recyclerView:RecyclerView=findViewById(R.id.recylerView)
        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        loadData()


    }
    private fun loadData(){

        val retrofit=Retrofit.Builder()
            .baseUrl(FIRST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(FootballAPI::class.java)
            compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))






        /*

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
         */
    }

private fun handleResponse(footbaList:List<FootballModel>){
    footbaList.let {
        footballModels= ArrayList(it)
        footballModels?.let {
            recyclerViewAdapter=RecyclerViewAdapter(it,this@MainActivity)
            val recyclerView:RecyclerView=findViewById(R.id.recylerView)
            recyclerView.adapter=recyclerViewAdapter
        }
    }
}

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()

    }
}

