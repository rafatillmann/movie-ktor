package com.studio.models

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: String,
    val name: String
)

val movieStorage = mutableListOf<Movie>()