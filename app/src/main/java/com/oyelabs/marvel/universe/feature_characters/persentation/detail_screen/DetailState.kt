package com.oyelabs.marvel.universe.feature_characters.persentation.detail_screen

import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterDetails

data class DetailState(
    val characterDetail :CharacterDetails = CharacterDetails(),
    val  isLoading : Boolean = true
)