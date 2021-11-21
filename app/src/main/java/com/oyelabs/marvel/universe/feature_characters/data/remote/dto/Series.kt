package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)