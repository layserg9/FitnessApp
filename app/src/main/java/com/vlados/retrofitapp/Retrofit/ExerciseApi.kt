package com.vlados.retrofitapp.Retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExerciseApi {
    @GET("/api/v2/exerciseinfo/{id}/")
    fun getExerciseById(@Path("id") id: Int): Single<Exercise>

    @GET("/api/v2/exerciseinfo/")
    fun getExerciseList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<ExerciseListResponse>
}
