package com.example.moviess.data.remote

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
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): PopularMovies

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRated

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") language: String,
        @Query("page") page: Int
    ): UpComing

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("/3/search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchMovies

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/genre/movie/list")
    suspend fun getGenresMovies(
        @Query("language") language: String,
    ): GenresResponse

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )

    @GET("3/movie/{movie_id}")
    suspend fun getDetailsForPoster(
        @Path("movie_id") id: Int,
        @Query("language") language: String
    ): Detail

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/movie/{movie_id}/credits")
    suspend fun getPeople(
        @Path("movie_id") id: Int,
        @Query("language") language: String

    ): Person

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/movie/{movie_id}/videos")
    suspend fun getVideo(
        @Path("movie_id") id: Int,
        @Query("language") language: String
    ): TrailerResponse

    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODQ0ZDljNGU1YmU1YmY4NGExOTY4ZGFiN2RlYzkyNyIsInN1YiI6IjY1NGY4NGI1ZDRmZTA0MDEzOTdmNDNlNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zmFnS9rc1Idh8pZyOjdbPrzmc3tLeHm8NWcmY6VSSh0"
    )
    @GET("3/discover/movie")
    suspend fun getMovieByGenres(
        @Query("with_genres") genres: String,
        @Query("language") language: String,
        @Query("page") page :Int
    ): GenreMoviesResponse

}