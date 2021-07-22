package com.example.rdiandroiddevelopertest.feature.movie.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rdiandroiddevelopertest.databinding.ActivityMovieListBinding
import com.example.rdiandroiddevelopertest.enum.Constant
import com.example.rdiandroiddevelopertest.feature.movie.adapter.MovieAdapter
import com.example.rdiandroiddevelopertest.feature.movie.viewmodel.MovieViewModel
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.model.Movie
import com.example.rdiandroiddevelopertest.repository.NavigatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MovieListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieListBinding
    private var movieViewModel:MovieViewModel? = null
    private lateinit var movieAdapter:MovieAdapter
    private var genreId:String? = null
    lateinit var movieds: ArrayList<ItemsMovie>
    var page = 1
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        genreId = intent.getStringExtra(Constant.genreId)
        Log.d(Constant.FINDBUG, "onCreate: $genreId")
        loadMovieList(genreId,1)
        initScrollListener()
    }

    private fun loadMovieList(genreId: String?,page:Int) {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        this?.let {
            movieViewModel!!.getMovie(genreId!!,page)?.observe(it, Observer { movie->
                initRecycler(genreId,movie)
                movieds = movie.results
            })
        }
    }

    private fun initRecycler(genreId: String, movie: Movie?) {
        movieAdapter = MovieAdapter(this, movie!!)
        binding.recyclerMovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
            movieAdapter.notifyDataSetChanged()
            movieAdapter!!.itemClickListener = object : MovieAdapter.Listener {
                override fun onClick(selected: ItemsMovie) {
                    NavigatorRepository.openDetailsMovieActivity(context,selected.id.toString())
                }
            }
        }
    }
    private fun initScrollListener() {
        binding.recyclerMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == movieds.size - 1) {
                        coroutineScope.launch {
                            loadMore()
                        }
                    }
                }



        })
    }

    private fun loadMore() {
        page+=1
        this?.let {
            binding.page.text = "halaman $page"
            movieViewModel!!.getMovie(genreId!!,page)?.observe(it, Observer { movie->
                movieAdapter = MovieAdapter(this, movie!!)
                movieAdapter.notifyDataSetChanged()
            })
        }

    }
}