package com.example.kotlin.tmbd

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {
    @GET("popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieObject

}