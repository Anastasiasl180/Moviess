package com.example.moviess.domain.use_case.HomePageUseCase


import com.example.moviess.common.Resource
import com.example.moviess.data.remote.dto.Cast
import com.example.moviess.data.remote.dto.Detail
import com.example.moviess.data.remote.dto.GenreMoviesResponse
import com.example.moviess.data.remote.dto.GenresResponse
import com.example.moviess.data.remote.dto.MovieVideoData
import com.example.moviess.data.remote.dto.PopularMovies
import com.example.moviess.data.remote.dto.SearchMovies
import com.example.moviess.data.remote.dto.TopRated
import com.example.moviess.data.remote.dto.UpComing
import com.example.moviess.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun topRated(language: String, page: Int): Flow<Resource<TopRated>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getTopRatedMovies(language, page)
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun upComing(language: String, page: Int): Flow<Resource<UpComing>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getUpComingMovies(language, page)
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun popular(language: String, page: Int): Flow<Resource<PopularMovies>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getPopularMovies(language, page)
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun searching(query: String, page: Int): Flow<Resource<SearchMovies>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getSearchMovies(query, page)
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun genres(language: String): Flow<Resource<GenresResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getGenresMovies(language)
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun details(language: String, id: Int): Flow<Resource<Detail>> = flow {
        try {
            emit(Resource.Loading())
            val detail = repository.getDetailsForPoster(language, id)
            emit(Resource.Success(detail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun crew(id: Int, language: String): Flow<Resource<List<Cast>>> = flow {
        try {
            emit(Resource.Loading())
            val people = repository.getPeople(id, language)
            emit(Resource.Success(people.cast))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun video(id: Int, language: String): Flow<Resource<List<MovieVideoData>>> = flow {
        try {
            emit(Resource.Loading())
            val video = repository.getVideo(id, language)
            emit(Resource.Success(video.results))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection"
                )
            )
        }
    }

    fun movieByGenres(genre: String, language: String, page: Int): Flow<Resource<GenreMoviesResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                val genres = repository.getMoviesByGenres(genre, language, page)
                emit(Resource.Success(genres))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection"
                    )
                )
            }
        }
}