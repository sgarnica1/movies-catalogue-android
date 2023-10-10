package com.example.kotlin.tmbd.data.repository

import android.util.Log
import com.example.kotlin.tmbd.data.remote.MovieAPIService
import com.example.kotlin.tmbd.domain.model.MovieObject
import com.example.kotlin.tmbd.data.remote.NetworkModelDI
import com.example.kotlin.tmbd.utils.Constants

class MovieRepository {
    private lateinit var api: MovieAPIService

    class MovieRepository(){}

    /**
     * @brief Obtener la lista de peliculas populares
     * @param page: pagina de la lista de peliculas
     */
    suspend fun getPopularMovies(page: Int): MovieObject?{
        val authToken = Constants.authToken
        api = NetworkModelDI(authToken, MovieAPIService::class.java)
        return try {
            api.getPopularMovies(page)
        } catch (e:java.lang.Exception) {
            e.printStackTrace()
            null
        }

    }
}