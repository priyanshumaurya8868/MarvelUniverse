package com.oyelabs.marvel.universe.feature_characters.domain.usecases

import com.oyelabs.marvel.universe.feature_characters.core.util.Resource
import com.oyelabs.marvel.universe.feature_characters.domain.model.Characters
import com.oyelabs.marvel.universe.feature_characters.domain.repo.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetCharacters(private val repo : MarvelRepository){

    operator fun invoke(query : String, limit :Int = 20, offset :Int = 0 ) : Flow<Resource<Characters>>{
        return repo.getChar(query,limit,offset)
    }
}