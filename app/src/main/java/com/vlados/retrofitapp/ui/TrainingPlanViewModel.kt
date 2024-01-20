package com.vlados.retrofitapp.ui

import com.vlados.retrofitapp.data.ExerciseRepository
import com.vlados.retrofitapp.data.TrainingPlanRepository
import com.vlados.retrofitapp.data.Weekdays
import io.reactivex.Flowable
import javax.inject.Inject

class TrainingPlanViewModel @Inject constructor(
    private val trainingPlanRepository: TrainingPlanRepository,
    private val exerciseListRepository: ExerciseRepository
) {

    fun getTrainingPlanList(): Flowable<List<ListItem>> {
        return trainingPlanRepository.getTrainingPlanMapFlow().map { sourceMap ->
            val resultList = mutableListOf<ListItem>()
            for (entry in sourceMap) {
                val exercises = entry.value
                val exerciseViewStateList = exercises.map { exercise ->
                    ListItem.ExerciseViewState(
                        id = exercise.id,
                        name = exercise.name,
                        description = exercise.description,
                        image = exercise.images.getOrNull(0)?.image,
                        weekday = entry.key
                    )
                }
                if (exerciseViewStateList.isNotEmpty()) {
                    resultList.add(ListItem.WeekDaysViewState(entry.key.name))
                    resultList.addAll(exerciseViewStateList)
                }
            }
            resultList.toList()
        }
    }

    fun deleteExerciseFromPlanList(dayName: Weekdays, selectedExerciseId: Int?) {
        val exercise = exerciseListRepository.getExerciseById(selectedExerciseId)
        if (exercise != null) {
            trainingPlanRepository.deleteExerciseFromTrainingPlanMap(dayName, exercise)
        }
    }
}

