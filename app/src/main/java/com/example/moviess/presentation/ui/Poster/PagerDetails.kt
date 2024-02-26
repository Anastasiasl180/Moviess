package com.example.moviess.presentation.ui.Poster

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.moviess.di.UserGlobalState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerDetails(
    viewModel: DetailsViewModel,onClickBack:() -> Unit
){
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    val enabledState by remember(pagerState.currentPage) {
        mutableStateOf(pagerState.currentPage == 0)
    }
    Box(modifier = Modifier.fillMaxSize()){
        VerticalPager(state = pagerState, userScrollEnabled =enabledState ) {

            if(it == 0){
                DetailsScreen(movie = viewModel.movieDetails.value, onClickBack = onClickBack)


            }else {
                DetailList(viewModel = viewModel, onClickBack =  onClickBack)
            }
        }
    }
}