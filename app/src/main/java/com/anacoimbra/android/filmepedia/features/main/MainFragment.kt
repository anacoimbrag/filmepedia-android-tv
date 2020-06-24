package com.anacoimbra.android.filmepedia.features.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.Observer
import com.anacoimbra.android.filmepedia.presenter.CardPresenter
import com.anacoimbra.android.filmepedia.R
import com.anacoimbra.android.filmepedia.features.detail.DetailActivity
import com.anacoimbra.android.filmepedia.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BrowseSupportFragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var backgroundManager: BackgroundManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        backgroundManager = BackgroundManager.getInstance(requireActivity())
        backgroundManager.attach(requireActivity().window)

        setupUi()
        prepareEntranceTransition()

        getMovies()
    }

    private fun setupUi() {
        title = getString(R.string.app_name)
        searchAffordanceColor =
            ResourcesCompat.getColor(
                resources,
                R.color.search_opaque, context?.theme
            )
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        onItemViewSelectedListener =
            OnItemViewSelectedListener { _, item, _, _ ->
                if (item is Movie) {
                    val metrics = DisplayMetrics()
                    requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
                    Glide.with(requireActivity())
                        .asDrawable()
                        .centerCrop()
                        .load("https://image.tmdb.org/t/p/w1280/${item.posterPath}")
                        .into(object :
                            CustomTarget<Drawable>(metrics.widthPixels, metrics.heightPixels) {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable>?
                            ) {
                                backgroundManager.drawable = resource
                            }

                        })
                }
            }

        onItemViewClickedListener =
            OnItemViewClickedListener { viewHolder, item, _, _ ->
                if (item is Movie) {
                    val bundle = (viewHolder.view as? ImageCardView)?.mainImageView?.let {
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            requireActivity(),
                            it,
                            "hero"
                        ).toBundle()
                    }
                    DetailActivity.start(requireContext(), item, bundle)
                }
            }

        setOnSearchClickedListener {
            Toast.makeText(activity, "To be done!", Toast.LENGTH_LONG).show()
        }
    }

    private fun getMovies() {
        progressBarManager.show()
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            progressBarManager.hide()
            val header = HeaderItem(1L, "Movies")
            val adapter = ArrayObjectAdapter(ListRowPresenter())
            val cardPresenter =
                CardPresenter()

            val cardsAdapter = ArrayObjectAdapter(cardPresenter)
            it.forEach { movie ->
                cardsAdapter.add(movie)
            }
            adapter.add(ListRow(header, cardsAdapter))

            this.adapter = adapter
        })
    }

}