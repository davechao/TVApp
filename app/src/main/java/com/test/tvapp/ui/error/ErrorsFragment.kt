package com.test.tvapp.ui.error

import android.os.Bundle
import android.support.v17.leanback.app.ErrorFragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.test.tvapp.R

class ErrorsFragment: ErrorFragment() {

    companion object {
        private val TRANSLUCENT = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.app_name)
    }

    fun setupView() {
        imageDrawable = ContextCompat.getDrawable(activity, R.drawable.lb_ic_sad_cloud)
        message = getString(R.string.error_fragment_message)
        setDefaultBackground(TRANSLUCENT)

        buttonText = getString(R.string.dismiss_error)
        buttonClickListener = View.OnClickListener {
            fragmentManager.beginTransaction().remove(this).commit()
        }
    }

}