package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

data class DataX(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultX>,
    val total: Int
)