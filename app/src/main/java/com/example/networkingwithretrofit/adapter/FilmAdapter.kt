package com.example.networkingwithretrofit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkingwithretrofit.databinding.ItemFilmBinding
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.ui.UpdateFilmActivity

class FilmAdapter(var listFilm: List<ResponseDataFilmItem>):RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemFilmBinding):RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listFilm[position].name
        holder.binding.tvDirector.text = listFilm[position].director
        holder.binding.tvDate.text = listFilm[position].date
        Glide.with(holder.itemView).load(listFilm[position].image).into(holder.binding.ivImg)

        holder.binding.ivEdit.setOnClickListener {
            val edit = Intent(it.context, UpdateFilmActivity::class.java)
            edit.putExtra("update", listFilm[position].id)
//            edit.putExtra("nama", listFilm[position].name)
            it.context.startActivity(edit)
        }
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}