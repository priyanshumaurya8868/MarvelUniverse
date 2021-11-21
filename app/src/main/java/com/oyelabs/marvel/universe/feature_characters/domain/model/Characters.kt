package com.oyelabs.marvel.universe.feature_characters.domain.model

import com.oyelabs.marvel.universe.feature_characters.core.util.merge

data class Characters(
    val count: Int  = 0,
    val limit: Int = 0,
    val offset: Int = 0,
    val total: Int =0 ,
    val charactersList: List<CharacterItem> = emptyList()
)
{
    fun add(newChars : Characters) :Characters{
        return Characters(
            count = count+newChars.count,
            limit=  newChars.limit,
            offset= newChars.offset,
            total = newChars.total,
            charactersList = merge(first = charactersList , second = newChars.charactersList)
        )
    }
}
