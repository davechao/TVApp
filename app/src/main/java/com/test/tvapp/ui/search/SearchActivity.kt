package com.test.tvapp.ui.search

import android.app.Activity
import android.os.Bundle
import com.test.tvapp.R
import android.content.Intent
import android.view.KeyEvent

class SearchActivity: Activity() {

    private lateinit var searchsFragment: SearchsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupView()
    }

    override fun onSearchRequested(): Boolean {
        if (searchsFragment.hasResults()) {
            startActivity(Intent(this, SearchActivity::class.java))
        } else {
            searchsFragment.startRecognition()
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT
                && !searchsFragment.hasResults()) {
            searchsFragment.focusOnSearch();
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun setupView() {
        searchsFragment = fragmentManager.findFragmentById(R.id.search_fragment) as SearchsFragment
    }

}