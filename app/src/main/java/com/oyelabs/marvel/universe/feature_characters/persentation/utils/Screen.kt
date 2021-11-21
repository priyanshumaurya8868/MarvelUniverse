package com.oyelabs.marvel.universe.feature_characters.persentation.utils

sealed class Screen(val route: String) {
    object CharactersScreen: Screen("characters_screen")
    object DetailScreen: Screen("detail_screen")
}