package com.example.moviess.presentation.ui.whishList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.dto.Detail
import com.example.moviess.presentation.ui.theme.Pink44

@Composable
fun WishList(viewModel: WishListViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(viewModel.details) { it ->
                Card(
                    //modifier = Modifier.clickable { viewModel.moviesId.size.let { clickToDetails(it) } }
                ) {
                    WishItem(movie = it, viewModel)

                }
            }
        }
    }
}

@Composable
fun WishItem(movie: Detail, viewModel: WishListViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)


    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            IconButton(onClick = { viewModel.deleteMovie(movie.id) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
            AsyncImage(
                model = Constants.BASE_IMAGE + movie.posterPath, contentDescription = "Image",
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
                            Card(colors = CardDefaults.cardColors(containerColor = Pink44)) {
                                Text(text = movie.releaseDate.split("-")[0], color = Color.White)

                            }
                        }
                        item {
                            Card(colors = CardDefaults.cardColors(containerColor = Pink44)) {

                                if (movie.adult) {
                                    Text(
                                        text = "18+",
                                        color = Color.White,
                                        modifier = Modifier.height(35.dp)
                                    )
                                } else {
                                    Text(text = "Up tp 18", color = Color.White)
                                }
                            }
                        }

                    })

            }

        }

    }
}