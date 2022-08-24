package com.fernando.retrofit.repository

import com.fernando.retrofit.data.model.MovieList
import com.fernando.retrofit.data.model.PlantaList
import com.fernando.retrofit.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

    override suspend fun getPlants(): PlantaList = dataSource.getPlants()
}