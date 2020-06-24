package com.anacoimbra.android.filmepedia.presenter

import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.anacoimbra.android.filmepedia.R
import com.anacoimbra.android.filmepedia.model.Movie
import com.bumptech.glide.Glide

class CardPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val card = object : ImageCardView(parent?.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }
        card.isFocusable = true
        card.isFocusableInTouchMode = true
        updateCardBackgroundColor(card, false)
        return ViewHolder(card)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        if (item is Movie) {
            val card = viewHolder?.view as? ImageCardView
            card?.let {
                it.titleText = item.title
                it.contentText = item.overview
                it.setMainImageDimensions(320, 180)
                Glide.with(it)
                    .load("https://image.tmdb.org/t/p/w1280${item.backdropPath}")
                    .centerCrop()
                    .error(R.drawable.default_background)
                    .into(it.mainImageView)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        val cardView = viewHolder?.view as? ImageCardView
        cardView?.badgeImage = null
        cardView?.mainImage = null
    }

    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) R.color.selected_background else R.color.default_background
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundResource(color)
        view.setInfoAreaBackgroundColor(ResourcesCompat.getColor(view.resources, color, null))
    }
}