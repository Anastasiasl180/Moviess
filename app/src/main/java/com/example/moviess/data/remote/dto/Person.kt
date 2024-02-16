package com.example.moviess.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)