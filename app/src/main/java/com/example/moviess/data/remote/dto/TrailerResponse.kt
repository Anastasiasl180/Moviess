package com.example.moviess.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieVideoData>
)