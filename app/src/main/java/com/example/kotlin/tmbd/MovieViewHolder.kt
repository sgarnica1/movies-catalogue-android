package com.example.kotlin.tmbd

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.tmbd.databinding.ItemMovieBinding

class MovieViewHolder (private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieBase){
        binding.TVName.text = item.title
    }
}