package com.example.moviess.presentation.ui.Poster

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.di.UserGlobalState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailList(viewModel: DetailsViewModel, onClickBack: () -> Unit) {
    if (viewModel.movieDetails.value != null) {

        val detail by remember {
            mutableStateOf(viewModel.movieDetails.value!!)
        }
        val person by remember {
            viewModel.people
        }
        val video by remember {
            viewModel.video
        }

        Scaffold(
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
                    title = { /*TODO*/ }, navigationIcon = {
                        IconButton(
                            onClick = { onClickBack() }, modifier = Modifier
                                .padding(top = 20.dp, end = 20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                    }
                )
            }, containerColor = Color.Green
        ) { padding ->

            AsyncImage(
                model = Constants.BASE_IMAGE + detail.posterPath,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .blur(10.dp),
                contentScale = ContentScale.Crop
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                Modifier.padding(bottom = 15.dp)
            ) {

                item() {


                    Column(
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .fillMaxSize()
                            .offset(y = 250.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.White)
                    ) {
                        Spacer(modifier = Modifier.padding(top = 70.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {


                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = detail.title,
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    textAlign = TextAlign.Center

                                )

                            }
                            Text(
                                text = detail.releaseDate,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 5.dp, start = 155.dp)
                            )
                            Row(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                horizontalArrangement = Arrangement.spacedBy(40.dp)
                            ) {
                                Card() {
                                    Row(horizontalArrangement = Arrangement.Center) {
                                        Text(text = detail.voteAverage.roundVoteAverage(),)
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = "Star rating",
                                            tint = Color.Yellow
                                        )
                                    }

                                }
                                Card() {
                                    Text(text = detail.runtime.convert())
                                }
                                Card {
                                    Text(text = detail.genres.joinToString(",") { it.name }
                                        .split(",")[0])
                                }

                            }
                        }
                        Column(
                            modifier = Modifier.padding(top = 50.dp, start = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {


                            Text(text = "Story Line")
                            Text(
                                text = detail.overview,

                                )
                            Text(text = "Cast")

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {

                                items(person) {
                                    Column {
                                        AsyncImage(
                                            model = Constants.BASE_IMAGE + it.profilePath,
                                            contentDescription = "image",
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10.dp))
                                                .height(200.dp)
                                                .aspectRatio(9 / 16f),
                                            contentScale = ContentScale.FillBounds
                                        )
                                        Text(text = it.name,Modifier.padding(top = 10.dp))

                                    }

                                }
                            }
                        }

                        Box(modifier = Modifier.padding(40.dp)) {
                            YoutubePlayer(
                                id = video?.key ?: "",
                                lifecycleOwner = LocalLifecycleOwner.current
                            )
                        }


                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 30.dp)
                            .height(270.dp),
                        contentAlignment = Alignment.TopCenter,
                    ) {

                        AsyncImage(
                            model = Constants.BASE_IMAGE + detail.posterPath,
                            contentDescription = "Centered image", modifier = Modifier.clip(
                                RoundedCornerShape(10.dp),
                            )

                        )
                        Box(modifier = Modifier.padding(top = 150.dp, start = 300.dp)) {
                            Row() {
                                IconButton(
                                    onClick = {
                                        viewModel.onClick(viewModel.movieDetails.value!!.id)
                                              Log.wtf("",viewModel.movieDetails.value.toString())}, modifier = Modifier
                                        .padding(top = 23.dp)
                                ) {
                                    if(viewModel.moviesId.contains(viewModel.movieDetails.value!!.id)) {
                                        Icon(imageVector =Icons.Filled.Favorite , contentDescription = "")
                                    }else{
                                        Icon(
                                            imageVector = Icons.Sharp.FavoriteBorder,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }


                                }

                                IconButton(
                                    onClick = { /*TODO*/ }, modifier = Modifier
                                        .padding(top = 23.dp, end = 35.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Sharp.Share,
                                        contentDescription = null,
                                        tint = Color.White
                                    )

                                }
                            }
                        }


                    }

                }
                item {
                    Spacer(modifier = Modifier.padding(130.dp))
                }
            }


        }

    }
}

@Composable
fun YoutubePlayer(
    id: String, lifecycleOwner: LifecycleOwner
) {
    AndroidView(factory = { context ->
        YouTubePlayerView(context = context).apply {
            lifecycleOwner.lifecycle.addObserver(this)

            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(id, 0f)


                }


            })
        }
    })
}







