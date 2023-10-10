package com.example.kotlin.tmbd

import com.google.gson.annotations.SerializedName
data class MovieObject(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: ArrayList<MovieBase>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
