package com.example.moviess.presentation.ui.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.data.remote.dto.Genre
import com.example.moviess.di.GlobalMovieDetails
import com.example.moviess.di.UserGlobalState
import com.example.moviess.domain.use_case.HomePageUseCase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val globalState: GlobalMovieDetails,
    private val globalUserState: UserGlobalState
) : ViewModel() {

    private val _stateOfPopularMovies = mutableStateOf(MovieState())
    val stateOfPopularMovies: State<MovieState> = _stateOfPopularMovies

    private val _stateOfUpComingMovies = mutableStateOf(MovieState())
    val stateOfUpComingMovies: State<MovieState> = _stateOfUpComingMovies

    private val _stateOfTopRatedMovies = mutableStateOf(MovieState())
    val stateOfTopRatedMovies: State<MovieState> = _stateOfTopRatedMovies

    private val _stateOfGenres = mutableStateOf(GenresState())
    val stateOfGenres: State<GenresState> = _stateOfGenres


    val imageBitMap get() = globalUserState.bitmapImage


    fun getUsername ():State<String>{
return globalUserState.username
    }


    fun setDetailsForPoster(id: Int) {
        globalState.setMovieDetails(id)
    }
    fun setGenres(genre: Genre){
        globalState.setGenres(genre)
    }




    init {
        getPopularMovies(
            Locale.getDefault().language, 1
        )
        getUpComingMovies(
            Locale.getDefault().language, 1

        )
        getTopRatedMovies(
            Locale.getDefault().language, 1

        )
        getGenres(
            Locale.getDefault().language
        )

    }


    private fun getPopularMovies(language: String, page: Int) {
        getMovieUseCase.popular(language, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateOfPopularMovies.value = MovieState(
                        movies = result.data?.movie ?: emptyList(),
                        page = result.data?.page ?: 0,
                        totalPage = result.data?.totalPages ?: 0
                    )
                }

                is Resource.Error -> {
                    _stateOfPopularMovies.value =
                        MovieState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _stateOfPopularMovies.value = MovieState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)

    }

    private fun getUpComingMovies(language: String, page: Int) {
        getMovieUseCase.upComing(language, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateOfUpComingMovies.value =
                        MovieState(
                            movies = result.data?.movie ?: emptyList(),
                            page = result.data?.page ?: 0,
                            totalPage = result.data?.totalPages ?: 0
                        )
                }

                is Resource.Error -> {
                    _stateOfPopularMovies.value =
                        MovieState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _stateOfPopularMovies.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies(language: String, page: Int) {
        getMovieUseCase.topRated(language, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateOfTopRatedMovies.value =
                        MovieState(
                            movies = result.data?.movie ?: emptyList(),
                            page = result.data?.page ?: 0,
                            totalPage = result.data?.totalPages ?: 0
                        )
                }

                is Resource.Error -> {
                    _stateOfPopularMovies.value =
                        MovieState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _stateOfPopularMovies.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getGenres(language: String) {
        getMovieUseCase.genres(language).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateOfGenres.value =
                        GenresState(
                            genres = result.data?.genres ?: emptyList()
                        )
                }

                is Resource.Error -> {
                    _stateOfGenres.value =
                        GenresState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _stateOfGenres.value = GenresState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}

