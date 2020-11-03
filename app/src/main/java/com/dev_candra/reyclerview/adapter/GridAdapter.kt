package com.dev_candra.reyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import com.dev_candra.reyclerview.R
import com.dev_candra.reyclerview.model.DataHero
import kotlinx.android.synthetic.main.layout_item_grid.view.*

class GridAdapter(private val context : Context,private val listHero : ArrayList<DataHero>) : RecyclerView.Adapter<GridAdapter.GridHeroViewHolder>() {

    class GridHeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero : DataHero){
            with(itemView){
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(350,350))
                    .into(photo_pahlawan)
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context,"Kamu memilih ${hero.name}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHeroViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_grid,parent,false)
        return GridHeroViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: GridHeroViewHolder, position: Int) {
       holder.bind(listHero[position])
    }
}