package com.fernando.retrofit.repository

import android.text.format.Time
import com.fernando.retrofit.application.AppConstants
import com.fernando.retrofit.data.model.MovieList
import com.fernando.retrofit.data.model.PlantaList
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WebService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

    @POST("/Request/getCentrosComerciales")
    suspend fun getPlants(): PlantaList

    /*@GET("/movie/{movie_id}/account_states")
    suspend fun getAccountStates(@Path("movie_id") id: Int, @Query("api_key") apiKey: String) : MovieList*/

}

object RetrofitClient {

    // Consultar a pruebas genesys
    /*private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()
    }*/

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            /*.client(httpClient)*/
            .build().create(WebService::class.java)
    }
}