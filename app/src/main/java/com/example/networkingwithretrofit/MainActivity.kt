package com.example.networkingwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingwithretrofit.adapter.NewsAdapter
import com.example.networkingwithretrofit.databinding.ActivityMainBinding
import com.example.networkingwithretrofit.model.ResponseDataNewsItem
import com.example.networkingwithretrofit.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataNews()
    }

    fun getDataNews(){
        RetrofitClient.RetrofitClient.instance.getAllNews()
            .enqueue(object : retrofit2.Callback<List<ResponseDataNewsItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if (response.isSuccessful){
                        var dataNews = response.body()
                        binding.rvNews.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvNews  .adapter = NewsAdapter(dataNews!!)
                    }else{
                        Toast.makeText(this@MainActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                }

            })
    }


}