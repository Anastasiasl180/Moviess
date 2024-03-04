package com.example.moviess.presentation.ui.Poster

import android.annotation.SuppressLint
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.dto.Detail
import com.example.moviess.data.remote.dto.Genre
import com.example.moviess.presentation.ui.searchScreen.searchBarScrollBehaviour
import com.example.moviess.presentation.ui.theme.color2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(movie: Detail?, onClickBack: () -> Unit) {


    if (movie != null) {
        Scaffold(
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onClickBack()
                            },
                            modifier = Modifier
                                .padding(top = 20.dp, end = 20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                    }

                )

            }, containerColor = Color.Black
        ) { padding ->

            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomEnd = 35.dp, bottomStart = 35.dp))
                            .fillMaxHeight(0.9f)
                    ) {
                        AsyncImage(
                            model = Constants.BASE_IMAGE + movie.posterPath,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    RoundedCornerShape(
                                        bottomEnd = 35.dp,
                                        bottomStart = 35.dp
                                    )
                                )
                                .shadow(100.dp)
                                .blur(300.dp)


                        )

                        Column() {
                            AsyncImage(
                                model = Constants.BASE_IMAGE + movie.posterPath,
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxHeight(0.67f)
                                    .graphicsLayer { alpha = 0.99f }
                                    .drawWithContent {
                                        val colors = listOf(
                                            Color.Black,
                                            Color.Transparent

                                        )
                                        drawContent()
                                        drawRect(

                                            brush = Brush.verticalGradient(colors),
                                            blendMode = BlendMode.DstIn,

                                            )
                                    }
                                    .clip(
                                        RoundedCornerShape(
                                            bottomEnd = 35.dp,
                                            bottomStart = 35.dp
                                        )
                                    )
                            )

                            Box() {


                                Column(modifier = Modifier
                                    .padding(bottom = 10.dp)) {

                                    Text(
                                        text = movie.title,
                                        color = Color.White,
                                        fontSize = 50.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .padding(
                                                top = 10.dp,
                                                start = 10.dp
                                            ), verticalArrangement = Arrangement.spacedBy(15.dp)
                                    ) {


                                        Text(
                                            text = movie.releaseDate,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.White
                                        )

                                        Text(
                                            text = movie.runtime.convert(),
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        //Text(text = roundVoteAverage(movie.voteAverage))
                                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                            Text(
                                                text = movie.voteAverage.roundVoteAverage(),
                                                color = Color.White, fontWeight = FontWeight.Medium
                                            )
                                            Icon(
                                                imageVector = Icons.Filled.Star,
                                                contentDescription = "Star rating",
                                                tint = Color.Yellow,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }



                                        Text(
                                            text = movie.genres.joinToString(",") { it.name },
                                            fontWeight = FontWeight.Medium,
                                            color = Color.White
                                        )

                                    }


                                }
                            }

                        }
                        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 710.dp),

                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null,
                                tint = Color.White
                            )

                        }
                    }

                    Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {

                        Text(
                            text = "Watch now!",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace, color = Color.White

                        )

                    }
                }


            }


        }
    }
}


/*fun roundVoteAverage(number:Double):String =
((number*10).toInt().toDouble()/10).toString()*/

fun Double.roundVoteAverage(): String =
    ((this * 10).toInt().toDouble() / 10).toString()

fun Int.convert(): String {

    val balance = this % 60
    val number = this - balance
    val hours = number / 60
    return "$hours h. $balance m."
}

@Composable
fun genres(genres: List<Genre>) {

    for (genre in genres) {
        Card {
            Text(text = genre.name)
        }

    }
}









