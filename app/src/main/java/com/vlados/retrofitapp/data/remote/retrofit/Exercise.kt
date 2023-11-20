package com.vlados.retrofitapp.data.remote.retrofit

data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<ExcerciseImage>
)
