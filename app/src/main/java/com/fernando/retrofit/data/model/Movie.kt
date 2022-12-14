package com.fernando.retrofit.data.model

import com.google.gson.annotations.SerializedName

/**
 * Clase que contiene los objetos del servidor de las peliculas en formato JSON
 */
data class Movie(
    val id: Int = -1,
    @SerializedName("adult")
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1
)

data class MovieList(val results: List<Movie> = listOf())