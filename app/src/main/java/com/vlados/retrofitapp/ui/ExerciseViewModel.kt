package com.vlados.retrofitapp.ui

import androidx.lifecycle.ViewModel
import com.vlados.retrofitapp.data.ExerciseDataBase
import com.vlados.retrofitapp.data.ExerciseRepository
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import io.reactivex.Flowable
import java.util.LinkedList
import javax.inject.Inject

class ExerciseViewModel @Inject constructor(
    private val repository: ExerciseRepository
) :
    ViewModel() {

    fun getExerciseListFlow(): Flowable<List<Exercise>> {
        return repository.getExerciseListFlow()
    }
    //TODO тут же можно добавить лоадер в конец списка(if next = true) + отфильтровать по языку

    fun updateExerciseList() {
        repository.updateExerciseList()
    }
}