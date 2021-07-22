package com.example.rdiandroiddevelopertest.api

import com.example.rdiandroiddevelopertest.enum.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieClient {
    private val retrofitClient: Retrofit.Builder by lazy {

        Retrofit.Builder()
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }
    val movieInterface:MovieInterface by lazy {
        retrofitClient.build().create(MovieInterface::class.java)
    }
}