package com.example.moviess.presentation.ui.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.dto.Genre

import com.example.moviess.data.remote.dto.Movie


@Composable
fun MovieList(movies: List<Movie>, tittle: String, onClick: (Movie) -> Unit,) {


    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text = tittle, color = Color.Black)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(movies) { movie ->
                MovieListItem(movie = movie) {
                    onClick(movie)
                }

            }

        }
    }
}

@Composable
fun MovieListItem(
    movie: Movie,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(300.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = Constants.BASE_IMAGE + movie.posterPath, contentDescription = "Image",
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
            Text(text = movie.title, modifier = Modifier.width(150.dp), color = Color.Black)
        }

    }
}

@Composable
fun GenreItem(name: String,onClick: () -> Unit) {
    Button(
        onClick = {onClick() },
        modifier = Modifier
            .padding(top = 120.dp, bottom = 10.dp)
            .padding(8.dp)
            .height(50.dp)
            .width(130.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        shape = RoundedCornerShape(10.dp),


    ) {
        Text(text = name, color = Color.White)
    }
}

@Composable
fun GenreItems(genres: List<Genre>,onClick:(Genre) -> Unit) {
    LazyRow {
        items(genres) { genre ->

            GenreItem(name = genre.name,onClick = {
                onClick(genre)
            })

        }
    }
}


