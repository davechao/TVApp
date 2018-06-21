package com.test.tvapp.repository

import com.test.tvapp.repository.model.Video

class VideoRepository {

    fun getVideoList(): List<Video> {
        val videoList = arrayListOf<Video>()

        val video1 = Video(
                id = 0,
                title = "Zeitgeist 2010_ Year in Review",
                description = "Fusce id nisi turpis. Praesent viverra bibendum semper.",
                studio = "Studio Zero",
                videoUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
                backgroundImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg",
                cardImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg"
        )

        val video2 = Video(
                id = 1,
                title = "Google Demo Slam_ 20ft Search",
                description = "Fusce id nisi turpis. Praesent viverra bibendum semper.",
                studio = "Studio One",
                videoUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
                backgroundImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
                cardImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg"
        )

        val video3 = Video(
                id = 2,
                title = "Introducing Gmail Blue",
                description = "Fusce id nisi turpis. Praesent viverra bibendum semper.",
                studio = "Studio Two",
                videoUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
                backgroundImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
                cardImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg"
        )

        val video4 = Video(
                id = 3,
                title = "Introducing Google Fiber to the Pole",
                description = "Fusce id nisi turpis. Praesent viverra bibendum semper.",
                studio = "Studio Three",
                videoUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
                backgroundImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
                cardImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg"
        )

        val video5 = Video(
                id = 4,
                title = "Introducing Google Nose",
                description = "Fusce id nisi turpis. Praesent viverra bibendum semper.",
                studio = "Studio Four",
                videoUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4",
                backgroundImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg",
                cardImageUrl = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
        )

        videoList.add(video1)
        videoList.add(video2)
        videoList.add(video3)
        videoList.add(video4)
        videoList.add(video5)
        return videoList
    }

}