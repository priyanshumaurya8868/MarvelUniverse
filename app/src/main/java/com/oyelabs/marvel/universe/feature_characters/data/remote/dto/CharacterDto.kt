package com.oyelabs.marvel.universe.feature_characters.data.remote.dto

import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterDetails

data class CharacterDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: DataX,
    val etag: String,
    val status: String
) {
    fun toCharacterDetails(): CharacterDetails {
        return data.results[0]?.let {
            CharacterDetails(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailPath = it.thumbnail.path,
                thumbnailExtention = it.thumbnail.extension,
                modified = it.modified
            )
        }
    }
}