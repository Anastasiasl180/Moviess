package com.example.moviess.domain.repository

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

interface MovieRepository {

    suspend fun getPopularMovies(language: String,page:Int):PopularMovies

    suspend fun getTopRatedMovies(language: String,page:Int):TopRated

    suspend fun getUpComingMovies(language: String,page:Int):UpComing

    suspend fun getSearchMovies(query:String,page: Int):SearchMovies

    suspend fun getGenresMovies(language: String):GenresResponse

    suspend fun getDetailsForPoster(language: String,id:Int):Detail

    suspend fun getPeople(id: Int,language: String):Person

    suspend fun getVideo(id: Int,language: String):TrailerResponse

    suspend fun getMoviesByGenres(genres:String,language: String,page: Int):GenreMoviesResponse
}