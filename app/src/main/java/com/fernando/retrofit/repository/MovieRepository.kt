package com.fernando.retrofit.repository

import com.fernando.retrofit.data.model.MovieList
import com.fernando.retrofit.data.model.PlantaList

interface MovieRepository {

    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
    suspend fun getPlants(): PlantaList

}