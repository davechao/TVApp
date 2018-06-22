package com.test.tvapp.ui.playback

import android.os.Bundle
import android.support.v4.app.FragmentActivity

class PlaybackActivity: FragmentActivity() {

    companion object {
        val KEY_VIDEO = "VIDEO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, PlaybackFragment())
                .commit()
    }
}