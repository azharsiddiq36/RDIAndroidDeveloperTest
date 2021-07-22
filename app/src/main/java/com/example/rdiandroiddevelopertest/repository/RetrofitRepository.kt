package com.example.rdiandroiddevelopertest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rdiandroiddevelopertest.api.MovieClient
import com.example.rdiandroiddevelopertest.enum.Constant
import com.example.rdiandroiddevelopertest.model.Author
import com.example.rdiandroiddevelopertest.model.Genre
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RetrofitRepository {
    private val responseGenre = MutableLiveData<Genre>()
    private val responseMovie = MutableLiveData<Movie>()
    private val responseDetailsMovie = MutableLiveData<ItemsMovie>()
    private val responseReviewMovie = MutableLiveData<Author>()

    fun getResponseReviewMovie(movieId: String,page:Int):MutableLiveData<Author>{
        val call = MovieClient.movieInterface.getReviewMovie(movieId,Constant.API_KEY,Constant.LANGUAGE,page)
        call.enqueue(object :Callback<Author>{
            override fun onResponse(call: Call<Author>, response: Response<Author>) {
                val data = response.body()
                responseReviewMovie.value = data!!
            }

            override fun onFailure(call: Call<Author>, t: Throwable) {
                Log.d(Constant.FINDBUG, "RetrofitRepository onResponse: $t")
            }

        })
        return responseReviewMovie
    }
    fun getResponseDetailsMovie(movieId: String):MutableLiveData<ItemsMovie>{
        val call = MovieClient.movieInterface.getDetailsMovie(movieId,Constant.API_KEY,Constant.LANGUAGE)
        call.enqueue(object : Callback<ItemsMovie>{
            override fun onResponse(call: Call<ItemsMovie>, response: Response<ItemsMovie>) {
                val data = response.body()
                responseDetailsMovie.value = data!!
            }

            override fun onFailure(call: Call<ItemsMovie>, t: Throwable) {
                Log.d(Constant.FINDBUG, "RetrofitRepository onResponse: $t")
            }

        })
        return responseDetailsMovie
    }
    fun getResponseGenre():MutableLiveData<Genre>{
        val call = MovieClient.movieInterface.getGenre(Constant.API_KEY,Constant.LANGUAGE)
        call.enqueue(object : Callback<Genre>{
            override fun onResponse(
                call: Call<Genre>,
                response: Response<Genre>
            ) {
                val data = response.body()
                responseGenre.value = data!!
            }

            override fun onFailure(call: Call<Genre>, t: Throwable) {
                Log.d(Constant.FINDBUG, "RetrofitRepository onResponse: 26 $t")
            }
        })
        return responseGenre
    }
    fun getResponseListMovieByGenre(movieId:String,page:Int):MutableLiveData<Movie>{
        val call = MovieClient.movieInterface.getListMovieByGenre(Constant.API_KEY,movieId,page)
        call.enqueue(object : Callback<Movie>{
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                val data = response.body()
                Log.d(Constant.FINDBUG, "onResponse: $response")
                responseMovie.value = data!!
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d(Constant.FINDBUG, "RetrofitRepository onResponse: 26 $t")
            }
        })
        return responseMovie
    }
}