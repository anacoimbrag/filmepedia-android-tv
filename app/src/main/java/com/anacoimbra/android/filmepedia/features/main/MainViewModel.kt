package com.anacoimbra.android.filmepedia.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.anacoimbra.android.filmepedia.model.Movie
import com.anacoimbra.android.filmepedia.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainViewModel(private val repository: MovieRepository) : ViewModel() {
    val movies: LiveData<List<Movie>> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(repository.getMovies())
        }
}