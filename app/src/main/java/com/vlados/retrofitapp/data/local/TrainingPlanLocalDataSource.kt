package com.vlados.retrofitapp.data.local

import com.vlados.retrofitapp.data.Weekdays
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class TrainingPlanLocalDataSource @Inject constructor() {
    private val trainingPlanMapProcessor = BehaviorProcessor.create<Map<Weekdays, List<Exercise>>>()

    fun getTrainingPlanMapFlow(): Flowable<Map<Weekdays, List<Exercise>>>{
        return trainingPlanMapProcessor
    }

    fun addExerciseToTrainingPlanMap(weekDay: Weekdays, exercise: List<Exercise>){
        val currentMap = trainingPlanMapProcessor.value ?: emptyMap()
        val newMap = currentMap.toMutableMap().apply {
            put(weekDay, exercise)
        }
        trainingPlanMapProcessor.onNext(newMap)
    }
}