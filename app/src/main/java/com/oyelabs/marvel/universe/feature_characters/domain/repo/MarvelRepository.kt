package com.oyelabs.marvel.universe.feature_characters.domain.repo

import com.oyelabs.marvel.universe.feature_characters.core.util.Resource
import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterDetails
import com.oyelabs.marvel.universe.feature_characters.domain.model.Characters
import kotlinx.coroutines.flow.Flow

interface MarvelRepository
{
    fun getChar(query : String?, limit :Int, offset :Int) : Flow<Resource<Characters>>
    fun getCharByID(id :Int) : Flow<Resource<CharacterDetails>>
}