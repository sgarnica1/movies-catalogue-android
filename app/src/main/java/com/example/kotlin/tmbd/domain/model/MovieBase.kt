package com.example.kotlin.tmbd.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @brief Modelo de datos de la respuesta de la API de TMDB
 * @see <a href="https://api.themoviedb.org/3/movie/popular">TMDB API</a>
 *
 * @property adult Indica si la película es para adultos
 * @property backdropPath Ruta de la imagen de fondo
 * @property genreIds Lista de géneros de la película
 * @property id Identificador de la película
 * @property originalLanguage Idioma original de la película
 * @property originalTitle Título original de la película
 * @property overview Sinopsis de la película
 * @property popularity Popularidad de la película
 * @property posterPath Ruta del póster de la película
 * @property releaseDate Fecha de estreno de la película
 * @property title Título de la película
 * @property video Indica si la película tiene vídeo
 * @property voteAverage Valoración media de la película
 * @property voteCount Número de votos de la película
 */
data class MovieBase (
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: ArrayList<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)