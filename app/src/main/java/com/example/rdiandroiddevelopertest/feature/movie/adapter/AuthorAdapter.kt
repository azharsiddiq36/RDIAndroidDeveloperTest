package com.example.rdiandroiddevelopertest.feature.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rdiandroiddevelopertest.R
import com.example.rdiandroiddevelopertest.databinding.ListItemReviewBinding
import com.example.rdiandroiddevelopertest.enum.Constant
import com.example.rdiandroiddevelopertest.model.Author
import com.example.rdiandroiddevelopertest.model.ItemsAuthor
import java.lang.Exception


class AuthorAdapter(private val context: Context, private val data: Author?):RecyclerView.Adapter<AuthorAdapter.MyViewHolder>() {
    var itemClickListener:Listener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_review, parent, false)
        return MyViewHolder(context, itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: AuthorAdapter.MyViewHolder, position: Int) {
        val authorData = getItem(position)
        holder.bind(authorData)


    }
    private fun getItem(n:Int): ItemsAuthor{
        return data!!.results[n]
    }

    override fun getItemCount(): Int {
        return data!!.results.size
    }
    class MyViewHolder(val context: Context, view: View,val listener:Listener?):RecyclerView.ViewHolder(view) {
        private val binding = ListItemReviewBinding.bind(view)
        fun bind(authorData: ItemsAuthor){
            try {
                Glide.with(context)
                    .load(Constant.IMAGE_URL+authorData.authorDetails.avatarPath)
                    .thumbnail(0.25f)
                    .centerCrop()
                    .error(R.drawable.ic_baseline_person_24)
                    .into(binding.imageView)
            }catch (e:Exception){

            }
            binding.tvName.text = authorData.author
            binding.tvText.text = authorData.content
//            binding.cvMovie.setOnClickListener {
//                listener?.onClick(authorData)
//            }

        }
    }
    interface Listener{
        fun onClick(selected: ItemsAuthor)
    }

}