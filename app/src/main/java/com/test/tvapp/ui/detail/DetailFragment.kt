package com.test.tvapp.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.app.DetailsFragment
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import com.test.tvapp.R
import com.test.tvapp.repository.model.Video
import com.test.tvapp.ui.detail.DetailActivity.Companion.KEY_VIDEO

class DetailFragment: DetailsFragment() {

    private lateinit var backgroundManager: BackgroundManager
    private lateinit var metrics: DisplayMetrics
    private var defaultBackground: Drawable? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareBackgroundManager()
        setupData()
    }

    private fun prepareBackgroundManager() {
        backgroundManager = BackgroundManager.getInstance(activity)
        backgroundManager.attach(activity.window)
        defaultBackground = ContextCompat.getDrawable(activity, R.drawable.default_background)
        metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
    }

    private fun setupData() {
        val video = activity.intent.getParcelableExtra<Video>(KEY_VIDEO)
    }

}