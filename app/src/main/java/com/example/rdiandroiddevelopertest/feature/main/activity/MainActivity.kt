package com.example.rdiandroiddevelopertest.feature.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rdiandroiddevelopertest.databinding.ActivityMainBinding
import com.example.rdiandroiddevelopertest.feature.main.adapter.GenreAdapter
import com.example.rdiandroiddevelopertest.feature.main.viewmodel.GenreViewModel
import com.example.rdiandroiddevelopertest.model.Genre
import com.example.rdiandroiddevelopertest.model.ItemsGenre
import com.example.rdiandroiddevelopertest.repository.NavigatorRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var genreAdapter:GenreAdapter? = null
    private lateinit var genreViewModel:GenreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadMovieGenre()

    }

    private fun loadMovieGenre() {
        genreViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)
        this?.let {
            genreViewModel.getGenre()?.observe(it, Observer { genre ->
                initrecycler(genre)

            })
        }
    }

    private fun initrecycler(genre: Genre) {
        genreAdapter = GenreAdapter(this, genre)
        binding.recyclerGenre.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = genreAdapter


            genreAdapter!!.itemClickListener = object : GenreAdapter.Listener {
                override fun onClick(selected: ItemsGenre) {
                    NavigatorRepository.openListMovieActivity(context,selected.id.toString())
                }
            }
        }
    }

}