package com.example.moviess.presentation.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    viewModel: MovieViewModel,navigateToDetails:()->Unit,navigateToJenre:() -> Unit
) {

    LazyColumn(modifier = Modifier.background(Color(0xFFECECEC)), verticalArrangement = Arrangement.SpaceBetween, content = {
        item {
            TextHello(viewModel.getUsername().value, onImageClick = {},viewModel.imageBitMap.value)
        }
        item {
            GenreItems(genres = viewModel.stateOfGenres.value.genres, onClick = {
                viewModel.setGenres(it)
                navigateToJenre()
            })


        }
        item {
            MovieList(
                movies = viewModel.stateOfPopularMovies.value.movies,
                tittle = "Popular Movies"
            ) {
viewModel.setDetailsForPoster(it.id)
                navigateToDetails()
            }

        }
        item {
            MovieList(
                movies = viewModel.stateOfTopRatedMovies.value.movies,
                tittle = "TopRated Movies"
            ){
                viewModel.setDetailsForPoster(it.id)
                navigateToDetails()
            }
        }
        item {
            MovieList(
                movies = viewModel.stateOfUpComingMovies.value.movies,
                tittle = "Up Coming Movies"
            ){
                viewModel.setDetailsForPoster(it.id)
                navigateToDetails()
            }
        }
    })


}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}



