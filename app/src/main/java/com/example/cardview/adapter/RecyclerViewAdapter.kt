package com.example.cardview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardview.R
import com.example.cardview.model.FootballModel
import com.example.cardview.view.MainActivity

class RecyclerViewAdapter(
    private val footbaList: ArrayList<FootballModel>,
    mainActivity: MainActivity
):RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){

    class RowHolder(view:View):RecyclerView.ViewHolder(view){

        fun bind(footballModel: FootballModel){
          val textView:TextView=itemView.findViewById(R.id.textPlayer)
          val textView2:TextView=itemView.findViewById(R.id.textTeam)
            val imageView:ImageView=itemView.findViewById(R.id.playerPhoto)
            textView.text=footballModel.name
            textView2.text=footballModel.team
            val imageUrl=footballModel.photo_url
            Glide.with(itemView.context).load(imageUrl).into(imageView)



        }


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RowHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return RowHolder(view)

    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
          holder.bind(footbaList[position])
    }

    override fun getItemCount(): Int {
        return footbaList.count()
    }


}