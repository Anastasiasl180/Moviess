package com.example.moviess.presentation.ui.whishList

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.data.remote.dto.Detail
import com.example.moviess.di.UserGlobalState
import com.example.moviess.domain.use_case.HomePageUseCase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(

    private val getMovieUseCase: GetMovieUseCase,
    private val userGlobalState: UserGlobalState


) : ViewModel() {
    val details = mutableStateListOf<Detail>()

    init {
        getMovies()
    }


    private fun getMovie(language: String, id: Int) = viewModelScope.launch {
        getMovieUseCase.details(language, id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { details.add(it) }
                }

                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovies() =
        userGlobalState.moviesId.value.forEach { id ->
            Log.wtf("", userGlobalState.moviesId.value.toString())
            getMovie(language = Locale.getDefault().language, id = id)


        }

    fun deleteMovie(id: Int) {
        userGlobalState.onFavouriteIconClick(id)
        details.removeIf { it.id == id }
        Log.wtf("", userGlobalState.moviesId.value.toString())

    }
}