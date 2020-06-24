package com.anacoimbra.android.filmepedia.features

import com.anacoimbra.android.filmepedia.features.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}