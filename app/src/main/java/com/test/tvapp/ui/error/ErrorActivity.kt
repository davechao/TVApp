package com.test.tvapp.ui.error

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.test.tvapp.R

class ErrorActivity: Activity() {

    companion object {
        private const val TIMER_DELAY = 3000L
    }

    private lateinit var errorFragment: ErrorsFragment
    private lateinit var spinnerFragment: SpinnerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        errorFragment = ErrorsFragment()
        fragmentManager
                .beginTransaction()
                .add(R.id.browse_fragment, errorFragment)
                .commit()

        spinnerFragment = SpinnerFragment()
        fragmentManager
                .beginTransaction()
                .add(R.id.browse_fragment, spinnerFragment)
                .commit()

        val handler = Handler()
        handler.postDelayed({
            fragmentManager
                    .beginTransaction()
                    .remove(spinnerFragment)
                    .commit()
            errorFragment.setupView()
        }, TIMER_DELAY)
    }

}