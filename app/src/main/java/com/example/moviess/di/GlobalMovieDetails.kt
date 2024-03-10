package com.example.moviess.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.moviess.data.remote.dto.Genre
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GlobalMovieDetails @Inject constructor(){

     private val _moviesDetails:MutableState<Int> = mutableStateOf(0)
    val moviesDetails:State<Int> = _moviesDetails

    private val _genres:MutableState<Genre> = mutableStateOf(Genre(0,""))
    val genres:State<Genre> = _genres


    fun setMovieDetails(id: Int){
        _moviesDetails.value = id
    }

    fun setGenres(genre: Genre){
        _genres.value = genre

    }

}