package com.anacoimbra.android.filmepedia.features.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.anacoimbra.android.filmepedia.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}