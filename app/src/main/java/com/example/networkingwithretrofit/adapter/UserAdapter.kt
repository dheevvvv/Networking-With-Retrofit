package com.example.networkingwithretrofit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkingwithretrofit.databinding.ItemFilmBinding
import com.example.networkingwithretrofit.databinding.ItemUserBinding
import com.example.networkingwithretrofit.model.ResponseDataFilmItem
import com.example.networkingwithretrofit.model.ResponseDataUserItem
import com.example.networkingwithretrofit.view.UpdateFilmActivity

class UserAdapter (var listUser: List<ResponseDataUserItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.binding.tvNameUser.text = listUser[position].name
        holder.binding.tvUsername.text = listUser[position].username
        holder.binding.tvAddress.text = listUser[position].address
        Glide.with(holder.itemView).load(listUser[position].image).into(holder.binding.ivImg)


    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}