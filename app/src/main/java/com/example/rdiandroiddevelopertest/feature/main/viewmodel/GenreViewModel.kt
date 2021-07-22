package com.example.rdiandroiddevelopertest.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rdiandroiddevelopertest.model.Genre
import com.example.rdiandroiddevelopertest.repository.RetrofitRepository

class GenreViewModel: ViewModel() {
    var serviceGenre:MutableLiveData<Genre>? = null
    fun getGenre():LiveData<Genre>?{
        serviceGenre = RetrofitRepository.getResponseGenre()
        return serviceGenre
    }
}