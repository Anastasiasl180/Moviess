package com.example.moviess.presentation.ui.homeScreen

import com.example.moviess.data.remote.dto.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val error:String = "",
    val movies:List<Movie> = emptyList(),
    val page:Int = 0,
    val totalPage:Int = 0


)
