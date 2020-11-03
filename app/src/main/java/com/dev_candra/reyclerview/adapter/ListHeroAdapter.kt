package com.dev_candra.reyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dev_candra.reyclerview.model.DataHero
import com.dev_candra.reyclerview.R
import kotlinx.android.synthetic.main.layout_hero_item.view.*

class ListHeroAdapter(private val listHero: ArrayList<DataHero>, private val context: Context): RecyclerView.Adapter<ListHeroAdapter.ListHeroViewHolder>() {

    private var oniItemCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.oniItemCallback = onItemClickCallback
    }

   inner class ListHeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero : DataHero){
            with(itemView){
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(60,60))
                    .into(img_itemPhoto)
                teksNama.text = hero.name
                teksDeskripsi.text = hero.deskripsi
                itemView.setOnClickListener {
                    oniItemCallback?.onItemClicked(hero)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(hero: DataHero)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHeroViewHolder {
        val view  = LayoutInflater.from(context).inflate(R.layout.layout_hero_item,parent,false)
        return ListHeroViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListHeroViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

}

