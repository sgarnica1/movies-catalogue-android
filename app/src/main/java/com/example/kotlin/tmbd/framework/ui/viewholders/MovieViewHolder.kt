package com.example.kotlin.tmbd.framework.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.tmbd.databinding.ItemMovieBinding
import com.example.kotlin.tmbd.domain.model.MovieBase

class MovieViewHolder (private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieBase){
        binding.TVName.text = item.title
    }
}