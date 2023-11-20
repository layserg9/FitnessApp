package com.vlados.retrofitapp.data.remote

import com.vlados.retrofitapp.data.remote.retrofit.ExerciseApi
import com.vlados.retrofitapp.data.remote.retrofit.ExerciseListResponse
import io.reactivex.Single
import javax.inject.Inject

class ExerciseRemoteDataSource @Inject constructor(private val exerciseApi: ExerciseApi) {
    private val limit = 20
    private val language = 2
    fun getExerciseList(offset: Int): Single<ExerciseListResponse> {
        return exerciseApi.getExerciseList(limit, offset, language)
    }

}
