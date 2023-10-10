package com.example.kotlin.tmbd.data.remote

import android.util.Log
import com.example.kotlin.tmbd.domain.model.MovieObject
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {
    @GET("popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieObject {
        Log.d("getPopularMovies", "getPopularMoviesAPI")
        return MovieObject(0, arrayListOf(), 0, 0)
    }
}