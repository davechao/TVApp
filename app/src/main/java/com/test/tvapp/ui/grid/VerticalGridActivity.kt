package com.test.tvapp.ui.grid

import android.app.Activity
import android.os.Bundle
import com.test.tvapp.R

class VerticalGridActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_grid)
        window.setBackgroundDrawableResource(R.drawable.grid_bg)
    }
}