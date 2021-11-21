package com.oyelabs.marvel.universe.feature_characters.persentation.display_characters

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.oyelabs.marvel.universe.feature_characters.core.util.ImageUtils
import com.oyelabs.marvel.universe.feature_characters.domain.model.CharacterItem
import com.oyelabs.marvel.universe.feature_characters.persentation.utils.Screen

@Composable
fun ItemCharacter(item: CharacterItem, navController: NavController) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.DarkGray)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate(route = Screen.DetailScreen.route + "?charId=${item.id}")
                }
        )
        {
            val imgUrl ="${item.thumbnailPath}/${ImageUtils.SquareAspectRation.STANDARD_X_LARGE}.${item.thumbnailExtention}"
            Log.d("omegaRanger", "Image_url :=> $imgUrl")
//
            Box(
                modifier = Modifier
                    .fillMaxHeight(.7f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = imgUrl
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
            )

            Text(
                text = item.name,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(5.dp)
            )

        }

    }
}


@ExperimentalFoundationApi
@Composable
fun FeatureSection(
    list: List<CharacterItem>,
    navController: NavController,
    state: LazyListState,
    viewModel: CharactersViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        LazyVerticalGrid(
            state = state,
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(list.size) {
                ItemCharacter(list[it], navController)
                Log.d("omegaRanger" , "item index = $it")
                viewModel.onCharacterListScrollPositionChange(it)
                if (it+1 >= viewModel.pageNo.value * viewModel.PAGE_SIZE && !viewModel.state.value.isLoading){
                    viewModel.pageIncrement()
                    Log.d("omegaRanger", "trigered pageNo. ${it+1} >=${viewModel.pageNo.value * viewModel.PAGE_SIZE} prevQuery : ${viewModel.prevQuery}")
                    viewModel.getCharacters()
                    viewModel.prevQuery = viewModel.searchQuery.value
                }
            }
        }
    }
}


