package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterItem
import com.oyelabs.marvel.universe.feature_characters.domain.model.Characters

data class CharactersDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
) {
    fun toCharacters(): Characters {
        return Characters(
            count = data.count,
            limit = data.limit,
            offset = data.offset,
            total = data.total,
            charactersList = data.results.map {
                CharacterItem(
                    id = it.id,
                    name = it.name,
                    thumbnailPath = it.thumbnail.path,
                    thumbnailExtention = it.thumbnail.extension,
                    modified = it.modified
                )
            }
        )
    }
}