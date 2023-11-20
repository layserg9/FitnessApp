package com.vlados.retrofitapp.data.local

import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class ExerciseLocalDataSource @Inject constructor() {
    private val exerciseListProcessor = BehaviorProcessor.create<List<Exercise>>()

    fun getExerciseListFlow(): Flowable<List<Exercise>> {
        return exerciseListProcessor
    }

    fun updateExerciseList(exercises: List<Exercise>) {
        val currentList = exerciseListProcessor.value?.toMutableList()
        if (currentList != null) {
            currentList.addAll(exercises)
            exerciseListProcessor.onNext(currentList)
        } else {
            exerciseListProcessor.onNext(exercises)
        }
    }

    fun getExerciseOffset(): Int {
        val currentList = exerciseListProcessor.value
        return currentList?.size ?: 0
        // аналогичная запись
//        return if (currentList == null) {
//            0
//        } else {
//            currentList.size
//        }
    }
    fun findExerciseById(id: Int?):Exercise?{
        val currentList = exerciseListProcessor.value
        return currentList?.find { it.id == id }
    }
}

