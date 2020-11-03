package com.dev_candra.reyclerview.adapter

import android.content.Context
import android.content.Intent
import android.opengl.GLDebugHelper
import android.telecom.Call
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dev_candra.reyclerview.R
import com.dev_candra.reyclerview.model.DataHero
import kotlinx.android.synthetic.main.layout_cardview_item.view.*

class CardViewAdapter(private val context: Context,private val listHero : ArrayList<DataHero>) : RecyclerView.Adapter<CardViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_cardview_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: CardViewAdapter.ViewHolder, position: Int) {
       holder.bind(listHero[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(dataHero: DataHero){
            with(itemView){
                tv_item_name.text = dataHero.name
                tv_item_description.text = dataHero.deskripsi
                // libarary yang menggunakan glide
                Glide.with(itemView.context) // digunakan untuk view
                    .load(dataHero.photo) // digunakan untuk mengload data
                    .apply(RequestOptions().override(350,350)) // digunakan untuk memanipulasi gambar
                    .into(img_item_photo) // untuk memasukkan gambar
                btn_set_favorite.setOnClickListener { 
                    Toast.makeText(itemView.context,"Favorite ${dataHero.name}",Toast.LENGTH_SHORT).show()
                }
                btn_set_share.setOnClickListener {
                    Toast.makeText(itemView.context,"Share ${dataHero.name}",Toast.LENGTH_SHORT).show()
                }
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context,"Kamu memilih ${dataHero.name}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}