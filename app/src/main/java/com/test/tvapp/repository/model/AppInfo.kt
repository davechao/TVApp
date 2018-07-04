package com.test.tvapp.repository.model

import android.graphics.drawable.Drawable

data class AppInfo(
        val appName: String = "",
        val packageName: String = "",
        val icon: Drawable
)