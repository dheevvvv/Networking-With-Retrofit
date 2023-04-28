package com.example.networkingwithretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingwithretrofit.adapter.FilmAdapter
import com.example.networkingwithretrofit.adapter.NewsAdapter
import com.example.networkingwithretrofit.databinding.ActivityFilmListBinding
import com.example.networkingwithretrofit.databinding.ActivityMainBinding
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class FilmListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFilm()
    }

    fun getDataFilm(){
        RetrofitClient.RetrofitClient.instance.getAllFilm()
            .enqueue(object : retrofit2.Callback<List<ResponseDataFilmItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>
                ) {
                    if (response.isSuccessful){
                        var dataFilm = response.body()
                        binding.rvFilm.layoutManager = LinearLayoutManager(this@FilmListActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvFilm.adapter = FilmAdapter(dataFilm!!)
                    }else{
                        Toast.makeText(this@FilmListActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    Toast.makeText(this@FilmListActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                }

            })
    }
}