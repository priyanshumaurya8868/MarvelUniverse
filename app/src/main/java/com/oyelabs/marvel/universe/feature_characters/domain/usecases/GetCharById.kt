package com.oyelabs.marvel.universe.feature_characters.domain.usecases

import com.oyelabs.marvel.universe.feature_characters.domain.repo.MarvelRepository

class GetCharById(private val repo: MarvelRepository) {

    operator fun invoke(id: Int) = repo.getCharByID(id)

}