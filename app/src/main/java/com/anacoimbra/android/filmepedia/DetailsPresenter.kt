package com.anacoimbra.android.filmepedia

import android.annotation.SuppressLint
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.anacoimbra.android.filmepedia.model.Movie

class DetailsPresenter : AbstractDetailsDescriptionPresenter() {
    @SuppressLint("SetTextI18n")
    override fun onBindDescription(vh: ViewHolder?, item: Any?) {
        if (item is Movie) {
            vh?.title?.text = item.title
            vh?.subtitle?.text = "★ ${item.voteAverage?.toString()}"
            vh?.body?.text = item.overview
        }
    }
}