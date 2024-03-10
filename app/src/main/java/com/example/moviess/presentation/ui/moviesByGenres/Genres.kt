package com.example.moviess.presentation.ui.moviesByGenres

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.dto.Movie
import com.example.moviess.presentation.ui.search_screen.searchBarScrollBehaviour
import com.example.moviess.presentation.ui.theme.color1
import com.example.moviess.presentation.ui.theme.color2
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreList(
    viewModel: GenresViewModel,
    navigateToPoster: () -> Unit,
    onClickBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val tioAppBarDefaults = searchBarScrollBehaviour()
    Scaffold(modifier = Modifier.nestedScroll(tioAppBarDefaults.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(title = { Text(text = viewModel.globalState.genres.value.name,     maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )},
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                scrollBehavior = tioAppBarDefaults,
                actions = {

                },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                })
        }
    ) {padding -> Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding =
            PaddingValues(start = 20.dp, end = 20.dp, top = 120.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp), state = scrollState


        ) {

            if (viewModel.moviesByGenre != null) {
                items(viewModel.moviesByGenre!!.results) { movies ->


                        GenresItem(movie = movies, clickToDetails = {
                            viewModel.globalState.setMovieDetails(it)
                            navigateToPoster()
                        })



                }
            }
            item {
                Pages(
                    viewModel = viewModel,
                    onClick = {
                        viewModel.getMovieByGenres(it)
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(0)
                        }

                    },
                )
            }

        }
    }
}}

@Composable
fun GenresItem(movie: Movie, clickToDetails: (Int) -> Unit) {

    ElevatedCard(
        modifier = Modifier
            .clickable { clickToDetails(movie.id) }
            .fillMaxWidth()
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

@Composable
fun Pages(viewModel: GenresViewModel, onClick: (Int) -> Unit) {


    if (viewModel.moviesByGenre != null) {


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            items(count = viewModel.moviesByGenre!!.totalPages) {

                TextButton(
                    onClick = {
                        onClick(it + 1)

                    },
                    shape = RoundedCornerShape(100),
                    modifier = Modifier
                        .shadow(20.dp)
                        .size(45.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                        if (viewModel.moviesByGenre?.page == it + 1) Color.DarkGray else Color.LightGray

                    )

                ) {
                    Text(text = (it + 1).toString(), color = color2)

                }

            }
        }
    }
}


