package com.oyelabs.marvel.universe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.oyelabs.marvel.universe.feature_characters.persentation.detail_screen.DetailScreen
import com.oyelabs.marvel.universe.feature_characters.persentation.display_characters.CharactersScreen
import com.oyelabs.marvel.universe.feature_characters.persentation.utils.Screen
import com.oyelabs.marvel.universe.ui.theme.MarvelUniverseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MarvelUniverseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CharactersScreen.route,
                    ) {


                        composable(Screen.CharactersScreen.route) {
                            CharactersScreen(navController = navController)
                        }

                        composable(
                            route = Screen.DetailScreen.route +
                                    "?charId={charId}",
                            arguments = listOf(
                                navArgument(
                                    name = "charId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            DetailScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

