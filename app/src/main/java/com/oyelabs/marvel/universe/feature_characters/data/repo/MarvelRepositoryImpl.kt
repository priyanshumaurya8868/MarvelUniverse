package com.oyelabs.marvel.universe.feature_characters.data.repo

import com.oyelabs.marvel.universe.feature_characters.core.util.Constants.PRIVATE_API_KEY
import com.oyelabs.marvel.universe.feature_characters.core.util.Constants.PUBLIC_API_KEY
import com.oyelabs.marvel.universe.feature_characters.core.util.Resource
import com.oyelabs.marvel.universe.feature_characters.core.util.md5
import com.oyelabs.marvel.universe.feature_characters.data.remote.MarvelApi
import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterDetails
import com.oyelabs.marvel.universe.feature_characters.domain.model.Characters
import com.oyelabs.marvel.universe.feature_characters.domain.repo.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MarvelRepositoryImpl(val api: MarvelApi) : MarvelRepository {



    override fun getChar(query: String?, limit: Int, offset: Int): Flow<Resource<Characters>> =
        flow {
            emit(Resource.Loading())
            try {
                val ts = System.currentTimeMillis()
                val hash = "$ts$PRIVATE_API_KEY$PUBLIC_API_KEY".md5()

                val remoteChars = if (query.isNullOrBlank()) {
                    api.getAllCharacters(
                        ts = ts,
                        apikey = PUBLIC_API_KEY,
                        hash = hash,
                        limit = limit,
                        offset = offset
                    ).toCharacters()
                } else {
                    api.getCharactersByName(
                        nameStartsWith = query,
                        ts = ts,
                        apikey = PUBLIC_API_KEY,
                        hash = hash,
                        limit = limit,
                        offset = offset
                    ).toCharacters()
                }
                emit(Resource.Success(remoteChars))

            } catch (e: HttpException) { // in case  of invalid response
                emit(
                    Resource.Error<Characters>(
                        message = "Oops, something went wrong!"
                    )
                )
            } catch (e: IOException) { // internet ,parsing... etc
                emit(
                    Resource.Error<Characters>(
                        message = "Couldn't reach server, check your internet connection."
                    )
                )
            }

        }

    override fun getCharByID(id: Int): Flow<Resource<CharacterDetails>> = flow {
        emit(Resource.Loading())
        try {
            val ts = System.currentTimeMillis()
            val hash = "$ts$PRIVATE_API_KEY$PUBLIC_API_KEY".md5()

            val remoteChar = api.getCharacterByID(id,ts, PUBLIC_API_KEY,hash).toCharacterDetails()
            emit(Resource.Success(remoteChar))
        } catch (e: HttpException) { // in case  of invalid response
            emit(
                Resource.Error<CharacterDetails>(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) { // internet ,parsing... etc
            emit(
                Resource.Error<CharacterDetails>(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }
}