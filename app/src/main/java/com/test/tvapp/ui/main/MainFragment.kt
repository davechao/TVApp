package com.test.tvapp.ui.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.app.BrowseFragment
import android.support.v17.leanback.widget.*
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.widget.Toast
import com.bumptech.glide.Glide
import com.test.tvapp.repository.model.Video
import com.test.tvapp.repository.VideoRepository
import com.test.tvapp.R
import com.test.tvapp.presenter.CardPresenter
import com.test.tvapp.presenter.IconHeaderItemPresenter
import com.test.tvapp.ui.detail.DetailActivity
import android.graphics.Bitmap
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.Transition


class MainFragment: BrowseFragment(){

    private lateinit var backgroundManager: BackgroundManager
    private lateinit var metrics: DisplayMetrics
    private var defaultBackground: Drawable? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareBackgroundManager()
        setupView()
        setupListener()
        setupData()
    }

    private fun prepareBackgroundManager() {
        backgroundManager = BackgroundManager.getInstance(activity)
        backgroundManager.attach(activity.window)
        defaultBackground = ContextCompat.getDrawable(activity, R.drawable.default_background)
        metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
    }

    private fun setupView() {
        badgeDrawable = activity.resources.getDrawable(R.drawable.videos_by_google_banner, null)
        title = getString(R.string.browse_title)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(activity, R.color.fastlane_background)
        searchAffordanceColor = ContextCompat.getColor(activity, R.color.search_opaque)
        setHeaderPresenterSelector(object: PresenterSelector(){
            override fun getPresenter(item: Any?): Presenter {
                return IconHeaderItemPresenter()
            }
        })
    }

    private fun setupListener() {
        setOnSearchClickedListener {
            Toast.makeText(activity, "Implement your own in-app search", Toast.LENGTH_SHORT).show()
        }
        onItemViewClickedListener = ItemViewClickedListener()
        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    private fun setupData() {
        val cardPresenter = CardPresenter()
        val cardAdapter = ArrayObjectAdapter(cardPresenter)

        val videoRepository = VideoRepository()
        val videoList = videoRepository.getVideoList()
        videoList.forEach {
            cardAdapter.add(it)
        }

        val listRowPresenter = ListRowPresenter()
        var listRowAdapter = ArrayObjectAdapter(listRowPresenter)
        val headerItem = HeaderItem(1, "Card Presenter")
        val listRow = ListRow(headerItem, cardAdapter)
        listRowAdapter.add(listRow)
        adapter = listRowAdapter
    }

    private inner class ItemViewClickedListener: OnItemViewClickedListener {
        override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder,
                item: Any,
                rowViewHolder: RowPresenter.ViewHolder,
                row: Row) {
            if(item is Video) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.KEY_VIDEO, item)
                startActivity(intent)
            }
        }
    }

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(itemViewHolder: Presenter.ViewHolder?, item: Any?,
                                    rowViewHolder: RowPresenter.ViewHolder, row: Row) {
            if(item is Video) {
                val backgroundImageUrl = Uri.parse(item.backgroundImageUrl)
                updateBackground(backgroundImageUrl.toString())
            }
        }
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