package com.example.networkingwithretrofit.networking

import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {
    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("film")
    fun getAllFilm(): Call<List<ResponseDataFilmItem>>
}