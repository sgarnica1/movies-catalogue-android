package com.example.kotlin.tmbd

class MovieRepository {
    private lateinit var api:MovieAPIService

    class MovieRepository(){}

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