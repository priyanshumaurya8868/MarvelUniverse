package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)