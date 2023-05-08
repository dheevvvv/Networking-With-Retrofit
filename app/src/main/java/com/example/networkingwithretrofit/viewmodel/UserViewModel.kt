package com.example.networkingwithretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.model.ResponseDataUserItem
import com.example.networkingwithretrofit.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel:ViewModel() {

    private val _userLiveData = MutableLiveData<List<ResponseDataUserItem>>()
    val userLiveData : LiveData<List<ResponseDataUserItem>> = _userLiveData

    init {
        _userLiveData
    }

    fun callApiUser(){
        RetrofitClient.RetrofitClient.instance.getAllUser().enqueue(object : Callback<List<ResponseDataUserItem>>{
            override fun onResponse(
                call: Call<List<ResponseDataUserItem>>,
                response: Response<List<ResponseDataUserItem>>
            ) {
                if (response.isSuccessful){
                    _userLiveData.postValue(response.body())
                }else{
                    _userLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                _userLiveData.postValue(null)
            }

        })
    }
}

