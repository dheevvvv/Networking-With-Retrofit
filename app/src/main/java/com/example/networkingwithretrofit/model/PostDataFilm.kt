package com.example.networkingwithretrofit.model

import com.google.gson.annotations.SerializedName

data class PostDataFilm(
    val date: String,
    val description: String,
    val director: String,
    val image: String,
    val name: String
)
