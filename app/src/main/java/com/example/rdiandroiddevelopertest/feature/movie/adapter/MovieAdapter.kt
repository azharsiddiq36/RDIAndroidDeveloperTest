package com.example.rdiandroiddevelopertest.feature.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rdiandroiddevelopertest.R
import com.example.rdiandroiddevelopertest.databinding.ListItemMovieBinding
import com.example.rdiandroiddevelopertest.enum.Constant
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.model.Movie


class MovieAdapter(private val context: Context, private val data: Movie):RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    var itemClickListener:Listener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MyViewHolder(context, itemView, itemClickListener)
    }
    override fun onBindViewHolder(holder: MovieAdapter.MyViewHolder, position: Int) {
        val MovieData = getItem(position)
        holder.bind(MovieData)


    }
    private fun getItem(n:Int): ItemsMovie {
        return data.results[n]
    }

    override fun getItemCount(): Int {
        return data.results.size
    }
    class MyViewHolder(val context: Context, view: View,val listener:Listener?):RecyclerView.ViewHolder(view) {
        private val binding = ListItemMovieBinding.bind(view)
        fun bind(MovieData: ItemsMovie){
            binding.tvMovie.text = MovieData.title
            Glide.with(context)
                .load(Constant.IMAGE_URL+MovieData.poster_path)
                .thumbnail(0.25f)
                .centerCrop()
                .error(R.drawable.ic_baseline_person_24)
                .into(binding.imageView)
            binding.cvMovie.setOnClickListener {
                listener?.onClick(MovieData)
            }

        }
    }
    interface Listener{
        fun onClick(selected:ItemsMovie)
    }

}