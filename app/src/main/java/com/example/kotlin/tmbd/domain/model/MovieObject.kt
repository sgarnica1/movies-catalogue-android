package com.example.kotlin.tmbd.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @brief Modelo de datos de la respuesta de la API de TMDB
 * @details Modelo de datos de la respuesta de la API de TMDB
 *
 * @property count Cantidad de resultados
 * @property results Lista de resultados
 * @property totalPages Cantidad de páginas
 * @property totalResults Cantidad total de resultados
 */
data class MovieObject(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: ArrayList<MovieBase>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
