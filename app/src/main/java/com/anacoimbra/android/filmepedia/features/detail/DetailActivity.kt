package com.anacoimbra.android.filmepedia.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.anacoimbra.android.filmepedia.R
import com.anacoimbra.android.filmepedia.model.Movie

class DetailActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        const val ARG_MOVIE = "movie"
        fun start(context: Context, movie: Movie, bundle: Bundle?) =
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_MOVIE, movie)
            }, bundle)
    }
}