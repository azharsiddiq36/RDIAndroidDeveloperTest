package com.example.rdiandroiddevelopertest.feature.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rdiandroiddevelopertest.model.Author
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.model.Movie
import com.example.rdiandroiddevelopertest.repository.RetrofitRepository

class AuthorViewModel: ViewModel() {
    var serviceReview:MutableLiveData<Author>? = null
    fun getReview(movieId:String,page:Int):LiveData<Author>?{
        serviceReview = RetrofitRepository.getResponseReviewMovie(movieId,page)
        return serviceReview
    }
}