package com.example.moviess.presentation.ui.Poster

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.data.remote.dto.Cast
import com.example.moviess.data.remote.dto.Crew
import com.example.moviess.data.remote.dto.Detail
import com.example.moviess.data.remote.dto.MovieVideoData
import com.example.moviess.di.GlobalMovieDetails
import com.example.moviess.di.UserGlobalState
import com.example.moviess.domain.use_case.HomePageUseCase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.internal.userAgent
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val globalState: GlobalMovieDetails,
    private val getMovieUseCase: GetMovieUseCase,
    private val userGlobalState: UserGlobalState
) : ViewModel() {

    private val _movieDetails: MutableState<Detail?> = mutableStateOf(null)
    val movieDetails: State<Detail?> = _movieDetails

    private val _people: MutableState<List<Cast>> = mutableStateOf(emptyList())
    val people: State<List<Cast>> = _people

    private val _video: MutableState<MovieVideoData?> = mutableStateOf(null)
    val video: State<MovieVideoData?> = _video

    init {
        getDetails(Locale.getDefault().language, globalState.moviesDetails.value)
        getPerson(globalState.moviesDetails.value, Locale.getDefault().language)
        getVideo(globalState.moviesDetails.value, Locale.getDefault().language)
    }

    val moviesId
        get() = userGlobalState.moviesId.value
    fun onClick(id: Int) {

        userGlobalState.onFavouriteIconClick(id)
    }


    private fun getDetails(language: String, id: Int) {
        getMovieUseCase.details(language, id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _movieDetails.value = result.data
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getPerson(id: Int, language: String) {
        getMovieUseCase.crew(id, language).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _people.value = result.data ?: emptyList()
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getVideo(id: Int, language: String) {
        getMovieUseCase.video(id, language).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _video.value = searchingTrailer(result.data ?: emptyList())
                }

                is Resource.Error -> {
                    Log.wtf("dfhhjfh", result.message)
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    fun searchingTrailer(movie: List<MovieVideoData>): MovieVideoData? {
        return movie.find { it.type == "Trailer" }
    }

}


