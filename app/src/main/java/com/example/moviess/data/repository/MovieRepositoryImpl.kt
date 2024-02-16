package com.example.moviess.data.repository

import com.example.moviess.data.remote.MovieApi
import com.example.moviess.data.remote.dto.Detail
import com.example.moviess.data.remote.dto.GenreMoviesResponse
import com.example.moviess.data.remote.dto.GenresResponse
import com.example.moviess.data.remote.dto.MovieVideoData
import com.example.moviess.data.remote.dto.Person
import com.example.moviess.data.remote.dto.PopularMovies
import com.example.moviess.data.remote.dto.SearchMovies
import com.example.moviess.data.remote.dto.TopRated
import com.example.moviess.data.remote.dto.TrailerResponse
import com.example.moviess.data.remote.dto.UpComing
import com.example.moviess.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getPopularMovies(language: String, page: Int): PopularMovies {
        return api.getPopularMovies(language, page)
    }

    override suspend fun getTopRatedMovies(language: String, page: Int): TopRated {
        return api.getTopRatedMovies(language, page)
    }

    override suspend fun getUpComingMovies(language: String, page: Int): UpComing {
        return api.getUpcoming(language, page)
    }

    override suspend fun getSearchMovies(query: String, page: Int): SearchMovies {
        return api.getSearchMovies(query, page)
    }

    override suspend fun getGenresMovies(language: String): GenresResponse {
        return api.getGenresMovies(language)
    }

    override suspend fun getDetailsForPoster(language: String,id:Int): Detail {
       return api.getDetailsForPoster(id,language)
    }

    override suspend fun getPeople(id: Int,language: String): Person {
        return api.getPeople(id,language)
    }

    override suspend fun getVideo(id: Int, language: String): TrailerResponse{
       return api.getVideo(id, language)
    }

    override suspend fun getMoviesByGenres(genres: String, language: String,page:Int): GenreMoviesResponse {
        return api.getMovieByGenres(genres,language,page)
    }
}