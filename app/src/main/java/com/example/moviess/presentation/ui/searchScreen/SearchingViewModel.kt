package com.example.moviess.presentation.ui.searchScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.data.remote.dto.Genre
import com.example.moviess.data.remote.dto.Movie
import com.example.moviess.domain.use_case.HomePageUseCase.GetMovieUseCase
import com.example.moviess.di.GlobalMovieDetails
import com.example.moviess.presentation.ui.Genres.GenresViewModel
import com.example.moviess.presentation.ui.homeScreen.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class SearchingViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase, private val globalState: GlobalMovieDetails
) : ViewModel() {


    private val _stateOfListOfSearching = mutableStateOf(MovieState())
    val stateOfListOfSearching: State<MovieState> = _stateOfListOfSearching

    private val _textState = mutableStateOf("")
    val textState = _textState

    private var genresForMovies: List<Genre> = emptyList()

    init {
        getGenres(Locale.getDefault().language)
    }


    fun getSearchMovies(query: String, page: Int) {
        getMovieUseCase.searching(query, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateOfListOfSearching.value =
                        MovieState(
                            movies = result.data?.movie ?: emptyList(),
                            page = result.data?.page ?: 0,
                            totalPage = result.data?.totalPages ?: 0
                        )
                }

                is Resource.Error -> {
                    _stateOfListOfSearching.value =
                        MovieState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _stateOfListOfSearching.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }

    fun setText(text: String) {
        _textState.value = text
        getSearchMovies(text, 1)

    }

    private fun getGenres(language: String) {
        getMovieUseCase.genres(language).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    genresForMovies = result.data?.genres ?: emptyList()
                }

                else -> {}

            }
        }.launchIn(viewModelScope)
    }

    fun getGenresForMovie(movie:Movie):List<Genre> =
        genresForMovies.filter {
            movie.genreIds.contains(it.id)
        }

    fun getDetailsForMovie(id:Int){
globalState.setMovieDetails(id)
    }



    fun fun1(name:Int){

        val variable = genresForMovies.let {name->
            name.get(0).id
        }
    }
}
