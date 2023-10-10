package com.example.kotlin.tmbd.data.repository

import android.util.Log
import com.example.kotlin.tmbd.data.remote.MovieAPIService
import com.example.kotlin.tmbd.domain.model.MovieObject
import com.example.kotlin.tmbd.data.remote.NetworkModelDI
import com.example.kotlin.tmbd.utils.Constants

class MovieRepository {
    private lateinit var api: MovieAPIService

    class MovieRepository(){}

    suspend fun getPopularMovies(page: Int): MovieObject?{
        Log.d("getPopularMovies", "getPopularMovies")
        val authToken = Constants.authToken
        Log.d("getPopularMovies", "$authToken")
        api = NetworkModelDI(authToken, MovieAPIService::class.java)
        Log.d("getPopularMovies", "$api")
        return try {
            val res = api.getPopularMovies(page)
            Log.d("getPopularMovies", "$res")
            res
        } catch (e:java.lang.Exception) {
            e.printStackTrace()
            Log.d("getPopularMovies", e.toString())
            null
        }

    }
}