package com.vlados.retrofitapp.data

import com.vlados.retrofitapp.data.local.TrainingPlanLocalDataSource
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import io.reactivex.Flowable
import javax.inject.Inject

class TrainingPlanRepository @Inject constructor(
    private val localDataSource: TrainingPlanLocalDataSource
) {
    fun getTrainingPlanMapFlow(): Flowable<Map<Weekdays, Set<Exercise>>>{
        return localDataSource.getTrainingPlanMapFlow()
    }

    fun addExerciseToTrainingPlanMap(weekDay: Weekdays, exercise: Set<Exercise>){
        localDataSource.addExerciseToTrainingPlanMap(weekDay, exercise)
    }
    fun deleteExerciseFromTrainingPlanMap(weekDay: Weekdays, exercise: Exercise){
        localDataSource.deleteExerciseFromTrainingPlanMap(weekDay, exercise)
    }
}