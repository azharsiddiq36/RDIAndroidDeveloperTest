package com.example.rdiandroiddevelopertest.feature.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rdiandroiddevelopertest.R
import com.example.rdiandroiddevelopertest.databinding.ActivityDetailsMovieBinding
import com.example.rdiandroiddevelopertest.enum.Constant
import com.example.rdiandroiddevelopertest.feature.movie.adapter.AuthorAdapter
import com.example.rdiandroiddevelopertest.feature.movie.viewmodel.AuthorViewModel
import com.example.rdiandroiddevelopertest.feature.movie.viewmodel.MovieViewModel
import com.example.rdiandroiddevelopertest.model.Author
import com.example.rdiandroiddevelopertest.model.ItemsAuthor
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.repository.NavigatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsMovieActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsMovieBinding
    private var movieViewModel:MovieViewModel? = null
    private var authorViewModel:AuthorViewModel? = null
    private lateinit var authorAdapter:AuthorAdapter
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var movieId = intent.getStringExtra(Constant.movieId)
        loadMovieData(movieId)
    }

    private fun loadMovieData(movieId: String?) {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        authorViewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
            this?.let{
                movieViewModel!!.getDetailsMovie(movieId!!)?.observe(it, Observer {movie->
                    initMovieDetail(movie)
                })
                coroutineScope.launch {
                    authorViewModel!!.getReview(movieId,1)?.observe(it, Observer { review->
                        initReview(review)
                    })
                }
            }

    }

    private fun initReview(review: Author?) {
        authorAdapter = AuthorAdapter(this, review)
        binding.recyclerReview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = authorAdapter
        }
    }

    private fun initMovieDetail(movie: ItemsMovie?) {
        var gen = ""
       for (genre in movie?.genres!!){
            gen +=  "${genre.name},"
        }
        binding.detailsMovie.text = movie?.title
        binding.tvVoteAverage.text = movie?.vote_average.toString()
        binding.tvOverview.text = movie?.overview.toString()
        binding.tvGenre.text = gen
        Log.d(Constant.FINDBUG, "initMovieDetail: "+Constant.IMAGE_URL+movie.poster_path)
        Glide.with(applicationContext)
            .load(Constant.IMAGE_URL+movie.poster_path)
            .thumbnail(0.25f)
            .centerCrop()
            .error(R.drawable.ic_launcher_background)
            .into(binding.ivCover)
    }
}