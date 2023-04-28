package com.example.networkingwithretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkingwithretrofit.databinding.ItemNewsBinding
import com.example.networkingwithretrofit.model.ResponseDataNewsItem

class NewsAdapter(var listNews: List<ResponseDataNewsItem>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(var binding:ItemNewsBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listNews[position].title
        holder.binding.tvAuthor.text = listNews[position].author
        holder.binding.tvDate.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].image).into(holder.binding.ivImg)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}