package com.vlados.retrofitapp


import android.annotation.SuppressLint
import android.util.Log
import com.vlados.retrofitapp.Retrofit.Exercise
import com.vlados.retrofitapp.Retrofit.ExerciseListResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val localDataSource: ExerciseLocalDataSource,
    private val remoteDataSource: ExerciseRemoteDataSource
) {

    fun getExerciseListFlow(): Flowable<List<Exercise>> {
        return localDataSource.getExerciseListFlow()
    }

    @SuppressLint("CheckResult")
    fun updateExerciseList() {
        remoteDataSource.getExerciseList(localDataSource.getExerciseOffset())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { exerciseListResponse: ExerciseListResponse ->
                    localDataSource.updateExerciseList(
                        exerciseListResponse.results
                    )
                },
                { error -> Log.d("VLADIK_APP", "$error") }
            )
    }
}

