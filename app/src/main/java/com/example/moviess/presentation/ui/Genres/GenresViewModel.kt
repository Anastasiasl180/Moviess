package com.example.moviess.presentation.ui.Genres

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.data.remote.dto.GenreMoviesResponse
import com.example.moviess.di.GlobalMovieDetails
import com.example.moviess.domain.use_case.HomePageUseCase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    val globalState: GlobalMovieDetails,
    private val getMoviesUseCase: GetMovieUseCase
) : ViewModel() {

    var moviesByGenre by mutableStateOf<GenreMoviesResponse?>(null)
        private set


    init {
        getMovieByGenres(1)
    }

    fun getMovieByGenres(page: Int) {
        getMoviesUseCase.movieFromGenres(
            globalState.genres.value.toString(),
            Locale.getDefault().language,
            page
        ).onEach { result ->

            when (result) {
                is Resource.Error -> {
                    Log.wtf("fgkggn", result.message)
                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    result.data?.let {
                        moviesByGenre = it
                    }
                }
            }

        }.launchIn(viewModelScope)

    }
}