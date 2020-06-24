package com.anacoimbra.android.filmepedia.api

import com.anacoimbra.android.filmepedia.BuildConfig
import com.anacoimbra.android.filmepedia.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataSource {

    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY): MovieResponse
}