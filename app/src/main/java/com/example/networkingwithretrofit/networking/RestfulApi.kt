package com.example.networkingwithretrofit.networking

import com.example.networkingwithretrofit.model.PostDataFilm
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestfulApi {
    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("film")
    fun getAllFilm(): Call<List<ResponseDataFilmItem>>

    @POST("film")
    fun postFilm(@Body request : PostDataFilm) : Call<ResponseDataFilmItem>

    @PUT("film/{id}")
    fun updateFilm(
        @Path("id") id:Int,
        @Body request: PostDataFilm
    ) : Call<ResponseDataFilmItem>
}