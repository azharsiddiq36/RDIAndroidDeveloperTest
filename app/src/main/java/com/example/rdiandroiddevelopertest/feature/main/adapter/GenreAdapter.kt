package com.example.rdiandroiddevelopertest.feature.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rdiandroiddevelopertest.R
import com.example.rdiandroiddevelopertest.databinding.ItemGenreBinding
import com.example.rdiandroiddevelopertest.model.Genre
import com.example.rdiandroiddevelopertest.model.ItemsGenre

class GenreAdapter(private val context: Context,private val data: Genre):RecyclerView.Adapter<GenreAdapter.MyViewHolder>() {
    var itemClickListener:Listener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return MyViewHolder(context, itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: GenreAdapter.MyViewHolder, position: Int) {
        val genreData = getItem(position)
        holder.bind(genreData)


    }
    private fun getItem(n:Int):ItemsGenre{
        return data.genres[n]
    }

    override fun getItemCount(): Int {
        return data.genres.size
    }
    class MyViewHolder(val context: Context, view: View,val listener:Listener?):RecyclerView.ViewHolder(view) {
        val binding = ItemGenreBinding.bind(view)
        fun bind(genreData: ItemsGenre){
            binding.tvGenre.text = genreData.name
            binding.cvGenre.setOnClickListener {
                listener?.onClick(genreData)
            }

        }
    }
    interface Listener{
        fun onClick(selected:ItemsGenre)
    }

}