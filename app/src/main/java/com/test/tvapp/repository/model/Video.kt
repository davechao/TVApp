package com.test.tvapp.repository.model

import io.mironov.smuggler.AutoParcelable

data class Video(
        var id: Long = 0,
        var title: String? = null,
        var description: String? = null,
        var backgroundImageUrl: String? = null,
        var cardImageUrl: String? = null,
        var videoUrl: String? = null,
        var studio: String? = null
): AutoParcelable