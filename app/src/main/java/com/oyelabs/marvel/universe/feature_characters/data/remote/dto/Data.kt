package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)