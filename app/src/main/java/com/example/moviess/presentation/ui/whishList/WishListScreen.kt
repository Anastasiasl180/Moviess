package com.example.moviess.presentation.ui.whishList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.dto.Detail
import kotlin.math.absoluteValue

@Composable
fun WishList(viewModel: WishListViewModel, onClickBack: () -> Unit) {
    val details = viewModel.details
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {


        Text(
            text = "Wishlist",
            modifier = Modifier.padding(start = 70.dp, top = 40.dp),
            fontSize = 25.sp,
            style = MaterialTheme.typography.labelMedium
        )
        val pagerState = rememberPagerState(pageCount = { details.size })

        HorizontalPager(
            pageSpacing = 60.dp,
            outOfBoundsPageCount = 2,
            state = pagerState,
            modifier = Modifier.fillMaxSize()

        ) { page ->
            WishItem(
                movie = details[page],
                viewModel = viewModel,
                page = page, pagerState = pagerState
            )
        }
        IconButton(
            onClick = {
                onClickBack()
            },
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp)
                .clickable { }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun WishItem(
    pagerState: PagerState,
    page: Int,
    movie: Detail,
    viewModel: WishListViewModel,
    modifier: Modifier = Modifier
) {

    val pageOffset = ((pagerState.currentPage - page) + pagerState
        .currentPageOffsetFraction).absoluteValue

    Card(modifier = modifier
        .padding(horizontal = 20.dp)
        .fillMaxHeight(0.7f)
        .fillMaxWidth()
        .graphicsLayer {
            val scale = lerp(1f, 1.25f, pageOffset)
            scaleX *= scale
            scaleY *= scale
        }
        .shadow(16.dp, ambientColor = Color.LightGray),
        shape = RoundedCornerShape(32.dp),
        colors = elevatedCardColors(containerColor = Color.White)) {
        Card(
            modifier = modifier
                .fillMaxSize()
                .shadow(16.dp, ambientColor = Color.LightGray),
            shape = RoundedCornerShape(32.dp),
            colors = elevatedCardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    model = Constants.BASE_IMAGE + movie.posterPath, contentDescription = "Image",
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .align(Alignment.Center)
                        .fillMaxHeight(0.55f)
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(16.dp))
                        .graphicsLayer {
                            val scale = lerp(1f, 1.75f, pageOffset)
                            scaleX *= scale
                            scaleY *= scale
                        }, contentScale = ContentScale.FillBounds
                )
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(bottom = 460.dp),
                    color = Color.Black,
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.headlineSmall
                )

                Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
                    IconButton(
                        onClick = { viewModel.deleteMovie(movie.id) },
                        modifier = Modifier.padding(top = 520.dp, start = 270.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                    }
                    Column(modifier = Modifier.padding(top = 390.dp)) {
                        Text(
                            text = movie.releaseDate.split("-")[0],
                            color = Color.Black
                        )
                        if (movie.adult) {

                            Text(
                                text = "18+",
                                color = Color.Black,
                                modifier = Modifier.height(35.dp)
                            )
                        } else {

                            Text(text = "Up tp 18", color = Color.Black)
                        }
                        Text(
                            movie.genres.joinToString { it.name }, color = Color.Black,
                        )

                    }
                }
            }

        }
    }
}






