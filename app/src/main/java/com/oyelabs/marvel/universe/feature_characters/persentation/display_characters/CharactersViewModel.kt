package com.oyelabs.marvel.universe.feature_characters.persentation.display_characters

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyelabs.marvel.universe.feature_characters.core.util.Resource
import com.oyelabs.marvel.universe.feature_characters.domain.model.Characters
import com.oyelabs.marvel.universe.feature_characters.domain.usecases.CharactersUseCase
import com.oyelabs.marvel.universe.feature_characters.persentation.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCase: CharactersUseCase
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    private val _state = mutableStateOf(CharacterState())
    val state: State<CharacterState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    val PAGE_SIZE = 20
    val pageNo = mutableStateOf(1)
    private var characterListScrollPosition = 0
    var prevQuery : String = ""



    init {
        getCharacters()
    }


    fun getCharacters(query: String = _searchQuery.value) {
        Log.d("omegaRanger", "quer : $query  pageNo. ${pageNo.value} pos : $characterListScrollPosition ")
         _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            if (query.isBlank() || prevQuery == query) {
                val offset = PAGE_SIZE*(pageNo.value-1)
                useCase.getCharacters(query,offset = offset).onEach { result ->
                    when (result) {
                        is Resource.Success -> {

                            _state.value = _state.value.copy(
                                characters = _state.value.characters.add(
                                    result.data ?: Characters()
                                ),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)

            } else {
                //query changed ->clear old data
                if(prevQuery != query){
                    _state.value = _state.value.copy(characters = Characters())
                    onCharacterListScrollPositionChange(0)
                    pageNo.value = 1
                }

                useCase.getCharacters(query).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                characters = result.data ?: Characters(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)

            }

        }

    }

    fun onCharacterListScrollPositionChange(pos: Int) {
        characterListScrollPosition = pos

    }

   fun pageIncrement(){
        pageNo.value = pageNo.value +1
    }



}




