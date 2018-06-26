package com.test.tvapp.ui.exoplayer

import android.net.Uri
import android.os.Bundle
import android.support.v17.leanback.app.VideoSupportFragment
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost
import android.support.v17.leanback.media.PlaybackTransportControlGlue
import com.google.android.exoplayer2.ExoPlayerFactory
import com.test.tvapp.repository.model.Video
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter
import com.test.tvapp.repository.VideoRepository
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.util.Util

class ExoPlayerFragment: VideoSupportFragment() {

    companion object {
        const val UPDATE_DELAY = 16
    }

    private lateinit var video: Video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupData()
        setupPlayer()
    }

    private fun setupData() {
        val videoRepository = VideoRepository()
        video = videoRepository.getVideoList()[0]
    }

    private fun setupPlayer() {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val exoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
        val playerAdapter = LeanbackPlayerAdapter(activity, exoPlayer, UPDATE_DELAY)

        val transportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        transportControlGlue.host = VideoSupportFragmentGlueHost(this)
        transportControlGlue.title = video.title
        transportControlGlue.subtitle = video.description
        transportControlGlue.playWhenPrepared()

        val mediaSourceUri = Uri.parse(video.videoUrl)
        val userAgent = Util.getUserAgent(activity, "VideoPlayerGlue")
        val mediaSource = ExtractorMediaSource(
                mediaSourceUri,
                DefaultDataSourceFactory(activity!!, userAgent),
                DefaultExtractorsFactory(),
                null,
                null)

        exoPlayer.prepare(mediaSource)
    }

}