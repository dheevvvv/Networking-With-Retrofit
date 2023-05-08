package com.example.networkingwithretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.networkingwithretrofit.databinding.ActivityUpdateFilmBinding
import com.example.networkingwithretrofit.viewmodel.FilmViewModel

class UpdateFilmActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateFilmBinding
    private lateinit var filmViewModel: FilmViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filmViewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        binding.btnSaveEdit.setOnClickListener {
            val fetchId = intent.getIntExtra("update", 0)
            val nama = binding.tvNameEdit.text.toString()
            val date = binding.tvDateEdit.text.toString()
            val desc = binding.tvDescEdit.text.toString()
            val director = binding.tvDirectorEdit.text.toString()
            val image = binding.tvImageEdit.text.toString()
            updateDataFilm(fetchId, name = nama, date = date, description = desc, director = director, image = image)
            finish()
        }

    }

    fun updateDataFilm(id:Int, date: String,
                       description: String,
                       director: String,
                       image: String,
                       name: String){
        filmViewModel.putApiFilm(id, date, description,director, image, name)
        filmViewModel.UpdateFilm().observe(this, Observer {
            if (it!=null){
                Toast.makeText(this, "Update Data Berhasil", Toast.LENGTH_SHORT).show()
            }
        })

    }
}