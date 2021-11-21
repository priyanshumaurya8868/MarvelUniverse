package com.oyelabs.marvel.universe.feature_characters.persentation.detail_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.oyelabs.marvel.universe.feature_characters.core.util.ImageUtils
import com.oyelabs.marvel.universe.feature_characters.persentation.utils.UIEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowSnackBar ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.msg
                    )
            }

        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Image(
                    painter = rememberImagePainter("${state.characterDetail.thumbnailPath}/${ImageUtils.PortraitAspectRatio.PORTRAIT_INCREDIBLE}.${state.characterDetail.thumbnailExtention}"),
                    contentDescription = null,
                    modifier = Modifier
                        .defaultMinSize(minHeight = 400.dp)
                        .width(300.dp)
                        .fillMaxHeight(.6f)
                        .border(5.dp, Color.Red),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                Text(
                    text = state.characterDetail.name,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = state.characterDetail.description,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )


            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
