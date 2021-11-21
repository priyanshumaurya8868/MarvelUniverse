package com.oyelabs.marvel.universe.feature_characters.data.remote

import com.oyelabs.marvel.universe.feature_characters.data.remote.dto.CharacterDto
import com.oyelabs.marvel.universe.feature_characters.data.remote.dto.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    suspend fun getCharactersByName(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharactersDto

    @GET("v1/public/characters")
    suspend fun getAllCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharactersDto

    @GET("v1/public/characters/{charID}")
    suspend fun getCharacterByID(
        @Path("charID") charID: Int,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): CharacterDto

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
    }
}