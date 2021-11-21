package com.oyelabs.marvel.universe.feature_characters.domain.model

data class CharacterItem(
    val id: Int = -1,
    val name: String ="",
    val thumbnailPath : String ="",
    val thumbnailExtention: String="",
    val modified: String="",
)

