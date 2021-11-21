package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class ResultX(
    val comics: ComicsX,
    val description: String,
    val events: EventsX,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesX,
    val stories: StoriesX,
    val thumbnail: ThumbnailX,
    val urls: List<UrlX>
)