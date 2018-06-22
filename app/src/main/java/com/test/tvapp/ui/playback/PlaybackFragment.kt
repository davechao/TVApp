package com.test.tvapp.ui.playback

import android.net.Uri
import android.os.Bundle
import android.support.v17.leanback.app.VideoSupportFragment
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost
import android.support.v17.leanback.media.MediaPlayerAdapter
import android.support.v17.leanback.media.PlaybackTransportControlGlue
import android.support.v17.leanback.widget.PlaybackControlsRow
import com.test.tvapp.repository.model.Video

class PlaybackFragment: VideoSupportFragment() {

    private lateinit var transportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    private lateinit var video: Video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupData()
        setupView()
    }

    override fun onPause() {
        super.onPause()
        transportControlGlue.pause()
    }

    private fun setupData() {
        video = activity!!.intent.getParcelableExtra<Video>(PlaybackActivity.KEY_VIDEO)
    }

    private fun setupView() {
        val playerAdapter = MediaPlayerAdapter(activity)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        transportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        transportControlGlue.host = VideoSupportFragmentGlueHost(this)
        transportControlGlue.title = video.title
        transportControlGlue.subtitle = video.description
        transportControlGlue.playWhenPrepared()

        playerAdapter.setDataSource(Uri.parse(video.videoUrl))
    }

}