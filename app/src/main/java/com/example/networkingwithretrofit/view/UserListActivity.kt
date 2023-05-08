package com.example.networkingwithretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingwithretrofit.adapter.FilmAdapter
import com.example.networkingwithretrofit.adapter.UserAdapter
import com.example.networkingwithretrofit.databinding.ActivityUserListBinding
import com.example.networkingwithretrofit.viewmodel.FilmViewModel
import com.example.networkingwithretrofit.viewmodel.UserViewModel

class UserListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserListBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataUser()

    }

    fun showDataUser(){
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.callApiUser()
        userViewModel.userLiveData.observe(this, Observer {
            if (it!=null){
                binding.rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvUser.adapter = UserAdapter(it)
            }
            else{
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
            }
        })
    }
}