package com.vlados.retrofitapp.ui

import androidx.lifecycle.ViewModel
import com.vlados.retrofitapp.data.ExerciseRepository
import com.vlados.retrofitapp.data.TrainingPlanRepository
import com.vlados.retrofitapp.data.Weekdays
import javax.inject.Inject

class AddExerciseToPlanBottomSheetViewModel @Inject constructor(
    private val exerciseListRepository: ExerciseRepository,
    private val trainingPlanRepository: TrainingPlanRepository
) : ViewModel() {
    val weekdaysArray: Array<String> = Weekdays.values().map { it.dayName }.toTypedArray()

    fun addExerciseToPlan(dayName: String, selectedExerciseId: Int?) {
        val selectedDay = Weekdays.values().find { it.dayName == dayName }
        val exercise = exerciseListRepository.getExerciseById(selectedExerciseId)
        if (selectedDay != null && exercise != null) {
            trainingPlanRepository.addExerciseToTrainingPlanMap(selectedDay, setOf(exercise))
        }
    }
}