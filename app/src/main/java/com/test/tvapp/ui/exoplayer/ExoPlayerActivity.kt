package com.test.tvapp.ui.exoplayer

import android.os.Bundle
import android.support.v4.app.FragmentActivity

class ExoPlayerActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, ExoPlayerFragment())
                .commit()
    }
}