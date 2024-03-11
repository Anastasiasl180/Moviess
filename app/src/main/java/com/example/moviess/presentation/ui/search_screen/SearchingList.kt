package com.example.moviess.presentation.ui.search_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.dto.Genre
import com.example.moviess.data.remote.dto.Movie
import com.example.moviess.presentation.ui.theme.color1
import com.example.moviess.presentation.ui.theme.color2

@Composable
fun SearchingMovies(
    movies: List<Movie>,
    getGenres: (Movie) -> List<Genre>,
    onClick: (Movie) -> Unit,
    viewModel: SearchingViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding =
        PaddingValues(),
        verticalArrangement = Arrangement.Center
    ) {
        if (movies.isEmpty()) {
            item {

                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {

                    Text(text = "No movie", color = Color.Black)


                }

            }
        } else {

            items(movies) { movies ->

                SearchingItem(movie = movies, getGenres = getGenres, onClick = { onClick(movies) })

            }

            item {
                Spacer(modifier = Modifier.padding(10.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    items(viewModel.stateOfListOfSearching.value.totalPage) {
                        TextButton(
                            onClick = {
                                viewModel.getSearchMovies(
                                    viewModel.textState.value,
                                    it
                                )
                                viewModel.setPage(it+1)
                            },
                            shape = RoundedCornerShape(100),
                            modifier = Modifier
                                .shadow(20.dp)
                                .size(45.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 30.dp,

                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                if (viewModel.currentPage.value == it+1) Color.DarkGray else Color.LightGray

                            )


                        ) {

                            Text(text = (it + 1).toString(), color = color2)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(7.dp))
            }


        }
    }
}

@Composable
fun SearchingItem(movie: Movie, getGenres: (Movie) -> List<Genre>, onClick: () -> Unit) {

    ElevatedCard(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 15.dp)
            .height(225.dp), colors = CardDefaults.cardColors(containerColor = color1)


    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = Constants.BASE_IMAGE + movie.posterPath, contentDescription = "Image2",
                modifier = Modifier

                    .fillMaxHeight()
                    .fillMaxWidth(0.43f)
                    .clip(RoundedCornerShape(10.dp)), contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(1.dp))
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.White
                )

                getGenres(movie).let {
                    if (it.size == 1) {
                        Card(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(0.45f),
                            colors = CardDefaults.cardColors(containerColor = color2)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                Alignment.Center
                            ) {
                                Text(
                                    getGenres(movie).joinToString { it.name }, color = Color.White,
                                )
                            }
                        }
                    } else {
                        Card(
                            modifier = Modifier
                                .padding(top = 10.dp, end = 15.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = color2)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                Alignment.Center
                            ) {
                                Text(
                                    getGenres(movie).take(2).joinToString { it.name },
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
                LazyVerticalStaggeredGrid(verticalItemSpacing = 20.dp,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    columns = StaggeredGridCells.Fixed(2),
                    content = {
                        item {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = color2),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
                                    Text(
                                        text = movie.releaseDate.split("-")[0],
                                        color = Color.White
                                    )
                                }


                            }
                        }
                        item {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = color2),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 15.dp)
                            ) {

                                if (movie.adult) {
                                    Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
                                        Text(
                                            text = "18+",
                                            color = Color.White,
                                            modifier = Modifier.height(35.dp)
                                        )
                                    }
                                } else {
                                    Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
                                        Text(text = "Up tp 18", color = Color.White)
                                    }
                                }
                            }
                        }
                    })

            }

        }

    }
}



