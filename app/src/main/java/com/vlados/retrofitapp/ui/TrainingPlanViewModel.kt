package com.vlados.retrofitapp.ui

import com.vlados.retrofitapp.data.TrainingPlanRepository
import com.vlados.retrofitapp.data.Weekdays
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import io.reactivex.Flowable
import javax.inject.Inject


class TrainingPlanViewModel @Inject constructor(
    private val trainingPlanRepository: TrainingPlanRepository
) {

    fun getTrainingPlanList(): Flowable<List<ListItem>> {
        val flowableMap = trainingPlanRepository.getTrainingPlanMapFlow()
        val mapToList = flowableMap.map { sourceMap ->
            val resultList = mutableListOf<ListItem>()
            for (entry in sourceMap) {
                val exercises = entry.value
                val exerciseViewStateList = exercises.map { exercise ->
                    ListItem.ExerciseViewState(
                        id = exercise.id,
                        name = exercise.name,
                        description = exercise.description,
                        image = exercise.images.getOrNull(0)?.image
                    )
                }
                resultList.add(ListItem.WeekDaysViewState(entry.key.name))
                resultList.addAll(exerciseViewStateList)
                }
            resultList.toList()
            }
        return mapToList
    }
}

