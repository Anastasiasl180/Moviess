package com.example.moviess.presentation.ui.homeScreen

import com.example.moviess.data.remote.dto.Genre
import com.example.moviess.data.remote.dto.GenresResponse

data class GenresState(
    val isLoading: Boolean = false,
    val error: String = "",
    val genres: List<Genre> = emptyList()
)