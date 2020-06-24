package com.anacoimbra.android.filmepedia.api

import com.anacoimbra.android.filmepedia.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideDataSource() }
}

fun provideDataSource(): MovieDataSource {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(MovieDataSource::class.java)
}

private val gson =
    GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .setPrettyPrinting()
        .create()

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .build()
}