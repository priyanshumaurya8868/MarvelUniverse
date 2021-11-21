package com.oyelabs.marvel.universe.feature_characters.persentation.display_characters

import com.oyelabs.marvel.universe.feature_characters.domain.model.Characters

data class CharacterState(
    val characters :Characters = Characters(),
    val isLoading : Boolean = false
)
