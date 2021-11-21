package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)