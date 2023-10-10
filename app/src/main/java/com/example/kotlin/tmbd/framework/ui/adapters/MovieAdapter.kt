package com.example.kotlin.tmbd.framework.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.tmbd.databinding.ItemMovieBinding
import com.example.kotlin.tmbd.domain.model.MovieBase
import com.example.kotlin.tmbd.framework.ui.viewholders.MovieViewHolder

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {
    var data:ArrayList<MovieBase> = ArrayList()
    lateinit var context: Context

    fun MovieAdapter(basicData : ArrayList<MovieBase>, context: Context){
        this.data = basicData
        this.context = context
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * @brief Actualiza los datos del adapter
     * @param newData Nuevos datos a mostrar
     * @return Unit
     */
    fun updateData(newData: List<MovieBase>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}