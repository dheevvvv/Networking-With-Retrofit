package com.example.networkingwithretrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingwithretrofit.R
import com.example.networkingwithretrofit.adapter.FilmAdapter
import com.example.networkingwithretrofit.databinding.ActivityFilmListBinding
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.networking.RetrofitClient
import com.example.networkingwithretrofit.viewmodel.FilmViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Response

class FilmListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmListBinding
    private lateinit var filmViewModel: FilmViewModel
    // declare the GoogleSignInClient
    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // call requestIdToken as follows
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnLogout.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }

        //trigger crashlytics
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        showDataFilm()
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddFilmActivity::class.java)
            startActivity(intent)
        }

    }

    fun showDataFilm(){
        filmViewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        filmViewModel.callApiFilm()
        filmViewModel.liveDataFilm.observe(this, Observer {
            if (it!=null){
                binding.rvFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvFilm.adapter = FilmAdapter(it.sortedByDescending{it.id})
            }
            else{
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
            }
        })
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