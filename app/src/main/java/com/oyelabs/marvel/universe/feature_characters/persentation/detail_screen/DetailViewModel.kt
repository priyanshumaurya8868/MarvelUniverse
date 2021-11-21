package com.oyelabs.marvel.universe.feature_characters.persentation.detail_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyelabs.marvel.universe.feature_characters.core.util.Resource
import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterDetails
import com.oyelabs.marvel.universe.feature_characters.domain.usecases.CharactersUseCase
import com.oyelabs.marvel.universe.feature_characters.persentation.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCase: CharactersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {



    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    init {
        savedStateHandle.get<Int>("charId")?.let { charId ->
            getCharById(charId)
        }?: Log.d("omegaRanger","KEY IS NULLLLLLLLL!")
    }

    private fun getCharById(id: Int) {
        Log.d("omegaRanger", "called with id = $id")
        viewModelScope.launch {

            useCase.getCharById(id).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            characterDetail = result.data ?: CharacterDetails(),
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

        }
    }
}