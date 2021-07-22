package com.example.rdiandroiddevelopertest.feature.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.model.Movie
import com.example.rdiandroiddevelopertest.repository.RetrofitRepository

class MovieViewModel: ViewModel() {
    var serviceMovie:MutableLiveData<Movie>? = null
    var serviceDetailMovie: MutableLiveData<ItemsMovie>? = null

    fun getMovie(genresId:String,page:Int):LiveData<Movie>?{
        serviceMovie = RetrofitRepository.getResponseListMovieByGenre(genresId,page)
        return serviceMovie
    }
    fun getDetailsMovie(movieId:String):LiveData<ItemsMovie>?{
        serviceDetailMovie = RetrofitRepository.getResponseDetailsMovie(movieId)
        return serviceDetailMovie
    }

}