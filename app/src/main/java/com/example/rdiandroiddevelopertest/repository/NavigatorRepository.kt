package com.example.rdiandroiddevelopertest.repository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.rdiandroiddevelopertest.enum.Constant
import com.example.rdiandroiddevelopertest.feature.movie.activity.DetailsMovieActivity
import com.example.rdiandroiddevelopertest.feature.movie.activity.MovieListActivity

object NavigatorRepository {
    fun openListMovieActivity(context: Context,genreId:String?){
        val intent = Intent(context, MovieListActivity::class.java)
        intent.putExtra(Constant.genreId,genreId)
        context.startActivity(intent)
    }
    fun openDetailsMovieActivity(context: Context,movieId:String?){
        val intent = Intent(context,DetailsMovieActivity::class.java)
        intent.putExtra(Constant.movieId,movieId)
        context.startActivity(intent)
    }
}