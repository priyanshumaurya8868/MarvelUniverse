package com.oyelabs.marvel.universe.feature_characters.domain.usecases

import com.oyelabs.marvel.universe.feature_characters.domain.repo.MarvelRepository

data class CharactersUseCase(
    val getCharById :  GetCharById,
    val getCharacters: GetCharacters
)