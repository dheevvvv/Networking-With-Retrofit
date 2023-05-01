package com.example.networkingwithretrofit.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingwithretrofit.adapter.FilmAdapter
import com.example.networkingwithretrofit.model.PostDataFilm
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class FilmViewModel:ViewModel() {

    private val _liveDataFilm = MutableLiveData<List<ResponseDataFilmItem>>()
    val liveDataFilm : LiveData<List<ResponseDataFilmItem>> = _liveDataFilm

    private val _liveDataAddFilm = MutableLiveData<ResponseDataFilmItem>()
    val liveDataAddFilm : LiveData<ResponseDataFilmItem> = _liveDataAddFilm

    private val _updateFilmLiveData = MutableLiveData<ResponseDataFilmItem>()
    val UpdateFilmLiveData : LiveData<ResponseDataFilmItem> = _updateFilmLiveData

    init {
        _liveDataFilm
        _liveDataAddFilm
        _updateFilmLiveData
    }

    fun callApiFilm(){
        RetrofitClient.RetrofitClient.instance.getAllFilm()
            .enqueue(object : retrofit2.Callback<List<ResponseDataFilmItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>
                ) {
                    if (response.isSuccessful){
                        _liveDataFilm.postValue(response.body())
                    }else{
                        _liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    _liveDataFilm.postValue(null)
                }

            })
    }

    fun postFilm():MutableLiveData<ResponseDataFilmItem>{
        return _liveDataAddFilm
    }

    fun UpdateFilm():MutableLiveData<ResponseDataFilmItem>{
        return _updateFilmLiveData
    }

    fun postApiFilm(date: String,
                    description: String,
                    director: String,
                    image: String,
                    name: String){
        RetrofitClient.RetrofitClient.instance.postFilm(PostDataFilm(date, description, director, image, name))
            .enqueue(object : retrofit2.Callback<ResponseDataFilmItem> {
                override fun onResponse(
                    call: Call<ResponseDataFilmItem>,
                    response: Response<ResponseDataFilmItem>
                ) {
                    if (response.isSuccessful){
                        _liveDataAddFilm.postValue(response.body())
                    }else{
                        _liveDataAddFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataFilmItem>, t: Throwable) {
                    _liveDataAddFilm.postValue(null)
                }

            })
    }

    fun putApiFilm(id:Int, date: String,
                   description: String,
                   director: String,
                   image: String,
                   name: String){

        RetrofitClient.RetrofitClient.instance.updateFilm(id, PostDataFilm(date, description, director, image, name))
            .enqueue(object :retrofit2.Callback<ResponseDataFilmItem>{
                override fun onResponse(
                    call: Call<ResponseDataFilmItem>,
                    response: Response<ResponseDataFilmItem>
                ) {
                    _updateFilmLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<ResponseDataFilmItem>, t: Throwable) {
                    _updateFilmLiveData.postValue(null)
                }

            })
    }
}