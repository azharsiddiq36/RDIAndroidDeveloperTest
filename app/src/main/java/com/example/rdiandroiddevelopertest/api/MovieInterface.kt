package com.example.rdiandroiddevelopertest.api

import com.example.rdiandroiddevelopertest.model.Author
import com.example.rdiandroiddevelopertest.model.Genre
import com.example.rdiandroiddevelopertest.model.ItemsMovie
import com.example.rdiandroiddevelopertest.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("/3/genre/movie/list")
    fun getGenre(
        @Query("api_key") api_key:String,
        @Query("language") language:String
    ): Call<Genre>
    @GET("/3/discover/movie")
    fun getListMovieByGenre(
        @Query("api_key") api_key:String,
        @Query("with_genres") genresId:String,
        @Query("page") page:Int
    ): Call<Movie>
    @GET("/3/movie/{movie_id}")
    fun getDetailsMovie(
        @Path("movie_id")movie_id:String?,
        @Query("api_key") api_key:String,
        @Query("language")language:String
    ):Call<ItemsMovie>
    @GET("/3/movie/{movie_id}/reviews")
    fun getReviewMovie(
        @Path("movie_id")movie_id:String,
        @Query("api_key")api_key: String,
        @Query("language")language: String,
        @Query("page")page: Int,
    ):Call<Author>
}