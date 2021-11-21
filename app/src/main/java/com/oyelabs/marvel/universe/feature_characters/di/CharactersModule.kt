package com.oyelabs.marvel.universe.feature_characters.di

import com.oyelabs.marvel.universe.feature_characters.data.remote.MarvelApi
import com.oyelabs.marvel.universe.feature_characters.data.repo.MarvelRepositoryImpl
import com.oyelabs.marvel.universe.feature_characters.domain.repo.MarvelRepository
import com.oyelabs.marvel.universe.feature_characters.domain.usecases.CharactersUseCase
import com.oyelabs.marvel.universe.feature_characters.domain.usecases.GetCharById
import com.oyelabs.marvel.universe.feature_characters.domain.usecases.GetCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: MarvelApi
    ): MarvelRepository {
        return MarvelRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: MarvelRepository) : CharactersUseCase{
        return CharactersUseCase(
            getCharacters = GetCharacters(repository),
            getCharById = GetCharById(repository)
        )
    }


    @Provides
    @Singleton
    fun provideDictionaryApi(): MarvelApi {
        return Retrofit.Builder()
            .baseUrl(MarvelApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
}