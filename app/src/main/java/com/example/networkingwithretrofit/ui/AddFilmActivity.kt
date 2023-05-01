package com.example.networkingwithretrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.networkingwithretrofit.databinding.ActivityAddFilmBinding
import com.example.networkingwithretrofit.databinding.ActivityFilmListBinding
import com.example.networkingwithretrofit.viewmodel.FilmViewModel

class AddFilmActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddFilmBinding
    private lateinit var filmViewModel: FilmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filmViewModel = ViewModelProvider(this).get(FilmViewModel::class.java)

        binding.btnSave.setOnClickListener {
            val date = binding.tvDateAdd.text.toString()
            val director = binding.tvDirectorAdd.text.toString()
            val description = binding.tvDescAdd.text.toString()
            val image = binding.tvImageAdd.text.toString()
            val name = binding.tvNameAdd.text.toString()
            addFilm(date, description, director, image,name)
            finish()
//            val intent = Intent(this, FilmListActivity::class.java)
//            startActivity(intent)
        }

    }

    fun addFilm(date: String,
                description: String,
                director: String,
                image: String,
                name: String){

        filmViewModel.postApiFilm(date, description, director, image, name)
        filmViewModel.postFilm().observe(this, Observer {
            if (it!= null){
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
        })

    }
}