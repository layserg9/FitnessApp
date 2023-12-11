package com.vlados.retrofitapp.di.ExerciseList

import com.vlados.retrofitapp.data.ExerciseDataBase
import com.vlados.retrofitapp.data.ExerciseRepository
import com.vlados.retrofitapp.ui.ExerciseViewModel
import dagger.Module
import dagger.Provides

@Module
class ExerciseViewModelModule {
    @Provides
    fun provideExerciseViewModel(repository: ExerciseRepository): ExerciseViewModel {
        return ExerciseViewModel(repository)
    }
}