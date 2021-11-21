package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: ThumbnailDto,
    val urls: List<Url>
)