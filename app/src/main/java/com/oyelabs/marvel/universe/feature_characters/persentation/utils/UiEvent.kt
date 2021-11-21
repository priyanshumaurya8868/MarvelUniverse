package com.oyelabs.marvel.universe.feature_characters.persentation.utils

sealed class UIEvent {
    data class ShowSnackBar(val msg: String) : UIEvent()
}