package com.example.moviess.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.moviess.data.remote.dto.Genre
import com.example.moviess.data.remote.dto.Movie
import com.example.moviess.presentation.ui.homeScreen.MovieState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GlobalMovieDetails @Inject constructor(){

     private val _moviesDetails:MutableState<Int> = mutableStateOf(0)
    val moviesDetails:State<Int> = _moviesDetails

    private val _genres:MutableState<Int> = mutableStateOf(0)
    val genres:State<Int> = _genres

    fun setMovieDetails(id: Int){
        _moviesDetails.value = id
    }

    fun setGenres(id: Int){
        _genres.value = id

    }

}