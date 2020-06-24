package com.anacoimbra.android.filmepedia.features.detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.*
import com.anacoimbra.android.filmepedia.DetailsPresenter
import com.anacoimbra.android.filmepedia.R
import com.anacoimbra.android.filmepedia.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlin.math.roundToInt

class DetailFragment : DetailsSupportFragment() {

    private lateinit var backgroundController: DetailsSupportFragmentBackgroundController
    private lateinit var objectAdapter: ArrayObjectAdapter
    private lateinit var presenter: ClassPresenterSelector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backgroundController = DetailsSupportFragmentBackgroundController(this)

        val movie =
            requireActivity().intent?.getParcelableExtra<Movie>(DetailActivity.ARG_MOVIE) ?: return

        presenter = ClassPresenterSelector()
        objectAdapter = ArrayObjectAdapter(presenter)

        setupDetailsHeader(movie)
        setupDetailsOverview()
        adapter = objectAdapter
        setupBackground(movie)
    }

    private fun setupDetailsHeader(movie: Movie) {
        val row = DetailsOverviewRow(movie)
        val width = convertDpToPixel(requireContext(), 380)
        val height = convertDpToPixel(requireContext(), 600)
        Glide.with(requireActivity())
            .asDrawable()
            .centerCrop()
            .load("https://image.tmdb.org/t/p/w1280/${movie.posterPath}")
            .into(object :
                CustomTarget<Drawable>(width, height) {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    row.imageDrawable = resource
                }

            })

        objectAdapter.add(row)
    }

    private fun setupDetailsOverview() {
        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(DetailsPresenter())
        detailsPresenter.backgroundColor =
            ResourcesCompat.getColor(resources, R.color.selected_background, null)

        val sharedElementHelper = FullWidthDetailsOverviewSharedElementHelper()
        sharedElementHelper.setSharedElementEnterTransition(requireActivity(), "hero")
        detailsPresenter.setListener(sharedElementHelper)
        detailsPresenter.isParticipatingEntranceTransition = true

        presenter.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)
    }

    private fun setupBackground(movie: Movie) {
        Glide.with(this)
            .asBitmap()
            .centerCrop()
            .load("https://image.tmdb.org/t/p/w1280${movie.backdropPath}")
            .error(R.drawable.default_background)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    backgroundController.coverBitmap = resource
                    backgroundController.enableParallax()
                    objectAdapter.notifyArrayItemRangeChanged(0, adapter.size())
                }
            })
    }

    private fun convertDpToPixel(context: Context, dp: Int): Int {
        val density = context.applicationContext.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }

}