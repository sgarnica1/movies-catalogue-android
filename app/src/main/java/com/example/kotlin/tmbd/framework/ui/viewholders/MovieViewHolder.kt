package com.example.kotlin.tmbd.framework.ui.viewholders

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.tmbd.databinding.ItemMovieBinding
import com.example.kotlin.tmbd.domain.model.MovieBase
import com.example.kotlin.tmbd.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewHolder (private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    /**
     * @brief Asigna los valores de la película a los elementos de la vista
     * @param item Película
     * @param context Contexto de la aplicación
     */
    fun bind(item: MovieBase, context: Context) {
        binding.TVName.text = item.title
        binding.TVRating.text = item.voteAverage.toString() + "/10"

        val croppedOverview = cropOverview(item.overview)
        binding.TVOverview.text = croppedOverview
        getMoviePoster(item.posterPath, context)
    }

    /**
     * @brief Acorta la descripción de la película para que no ocupe tanto espacio en la pantalla
     * @param overview Descripción de la película
     * @return Descripción acortada
     */
    private fun cropOverview(overview: String): String {
        val maxLength = 100
        return if (overview.length <= maxLength) {
            overview
        } else {
            overview.substring(0, maxLength) + "..."
        }
    }

    /**
     * @brief Obtiene el póster de la película
     * @param posterPath Ruta del póster
     * @param context Contexto de la aplicación
     * @return Descripción acortada
     */
    private fun getMoviePoster(posterPath: String, context: Context) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .priority(Priority.HIGH)

        val urlImage = Constants.SERVER_BASE_URL_IMAGE + posterPath

        // Load the image on the main thread using Glide
        Glide.with(context)
            .load(urlImage)
            .apply(requestOptions)
            .into(binding.IVPoster)
    }

}