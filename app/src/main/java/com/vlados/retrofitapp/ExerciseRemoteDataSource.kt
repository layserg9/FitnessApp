package com.vlados.retrofitapp

import com.vlados.retrofitapp.Retrofit.ExerciseApi
import com.vlados.retrofitapp.Retrofit.ExerciseListResponse
import io.reactivex.Single
import javax.inject.Inject

class ExerciseRemoteDataSource @Inject constructor(private val exerciseApi: ExerciseApi) {
    private val limit = 20
    fun getExerciseList(offset: Int): Single<ExerciseListResponse> {
        return exerciseApi.getExerciseList(limit, offset)
    }

}
