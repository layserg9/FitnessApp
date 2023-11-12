package com.vlados.retrofitapp.Retrofit

data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<Image>
)
