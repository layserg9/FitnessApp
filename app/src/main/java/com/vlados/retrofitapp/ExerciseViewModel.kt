package com.vlados.retrofitapp

import androidx.lifecycle.ViewModel
import com.vlados.retrofitapp.Retrofit.Exercise
import io.reactivex.Flowable
import javax.inject.Inject

class ExerciseViewModel @Inject constructor(private val repository: ExerciseRepository) : ViewModel() {

    fun getExerciseListFlow(): Flowable<List<Exercise>> {
        return repository.getExerciseListFlow()
    }

    fun updateExerciseList() {
        repository.updateExerciseList()
    }
}