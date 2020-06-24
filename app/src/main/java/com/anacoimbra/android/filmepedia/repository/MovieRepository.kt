package com.anacoimbra.android.filmepedia.repository

import com.anacoimbra.android.filmepedia.api.MovieDataSource
import com.anacoimbra.android.filmepedia.model.Movie
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get()) }
}

class MovieRepository(private val dataSource: MovieDataSource) {

    suspend fun getMovies(): List<Movie> =
        try {
            dataSource.getMovies().results.orEmpty()
        } catch (e: Exception) {
            emptyList()
        }
}