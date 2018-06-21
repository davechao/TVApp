package com.test.tvapp.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.app.DetailsFragment
import android.support.v17.leanback.widget.*
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.test.tvapp.R
import com.test.tvapp.presenter.DetailsDescriptionPresenter
import com.test.tvapp.repository.model.Video
import com.test.tvapp.ui.detail.DetailActivity.Companion.KEY_VIDEO
import com.test.tvapp.ui.detail.DetailActivity.Companion.SHARED_ELEMENT_NAME
import android.support.v17.leanback.widget.SparseArrayObjectAdapter
import android.support.v17.leanback.widget.DetailsOverviewRow
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter
import com.test.tvapp.presenter.DetailOverviewLogoPresenter

class DetailFragment: DetailsFragment() {

    companion object {
        val NO_NOTIFICATION = -1
        val ACTION_WATCH_TRAILER = 1
        val ACTION_RENT = 2
        val ACTION_BUY = 3
    }

    private lateinit var backgroundManager: BackgroundManager
    private lateinit var metrics: DisplayMetrics
    private lateinit var video: Video
    private lateinit var mAdapter: ArrayObjectAdapter
    private var defaultBackground: Drawable? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareBackgroundManager()
        setupData()
        setupView()
    }

    private fun prepareBackgroundManager() {
        backgroundManager = BackgroundManager.getInstance(activity)
        backgroundManager.attach(activity.window)
        defaultBackground = ContextCompat.getDrawable(activity, R.drawable.default_background)
        metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
    }

    private fun setupView() {
        setupAdapter()
        setupDetailsOverviewRow()
    }

    private fun setupAdapter() {
        val helper = FullWidthDetailsOverviewSharedElementHelper()
        helper.setSharedElementEnterTransition(activity, SHARED_ELEMENT_NAME)

        val rowPresenter = FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter(), DetailOverviewLogoPresenter())
        rowPresenter.backgroundColor = ContextCompat.getColor(activity, R.color.selected_background)
        rowPresenter.initialState = FullWidthDetailsOverviewRowPresenter.STATE_HALF
        rowPresenter.isParticipatingEntranceTransition = false
        prepareEntranceTransition()
        rowPresenter.setListener(helper)
        rowPresenter.setOnActionClickedListener {
            if (it.id.toInt() == ACTION_WATCH_TRAILER) {

            } else {
                Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        val presenterSelector = ClassPresenterSelector()
        presenterSelector.addClassPresenter(DetailsOverviewRow::class.java, rowPresenter)
        presenterSelector.addClassPresenter(ListRow::class.java, ListRowPresenter())
        mAdapter = ArrayObjectAdapter(presenterSelector)
        adapter = mAdapter
    }

    private fun setupDetailsOverviewRow() {
        val detailsOverviewRow = DetailsOverviewRow(video)

        val options = RequestOptions()
                .error(R.drawable.default_background)
                .dontAnimate()

        Glide.with(this)
                .asBitmap()
                .load(video.cardImageUrl)
                .apply(options)
                .into(object: SimpleTarget<Bitmap>() {
                    override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>) {
                        detailsOverviewRow.setImageBitmap(activity, resource)
                        startEntranceTransition()
                    }
                })

        val actionAdapter = SparseArrayObjectAdapter()
        actionAdapter.set(ACTION_WATCH_TRAILER,
                Action(ACTION_WATCH_TRAILER.toLong(),
                        getString(R.string.watch_trailer_1),
                        getString(R.string.watch_trailer_2)))
        actionAdapter.set(ACTION_RENT,
                Action(ACTION_RENT.toLong(),
                        getString(R.string.rent_1),
                        getString(R.string.rent_2)))
        actionAdapter.set(ACTION_BUY,
                Action(ACTION_BUY.toLong(),
                        getString(R.string.buy_1),
                        getString(R.string.buy_2)))
        detailsOverviewRow.actionsAdapter = actionAdapter
        mAdapter.add(detailsOverviewRow)
    }

    private fun setupData() {
        video = activity.intent.getParcelableExtra<Video>(KEY_VIDEO)
        updateBackground(video.backgroundImageUrl.toString())
    }

    private fun updateBackground(uri: String) {
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        val options = RequestOptions()
                .centerCrop()
                .error(defaultBackground)

        Glide.with(this)
                .asBitmap()
                .load(uri)
                .apply(options)
                .into(object: SimpleTarget<Bitmap>(width, height) {
                    override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>) {
                        backgroundManager.setBitmap(resource)
                    }
                })
    }

}