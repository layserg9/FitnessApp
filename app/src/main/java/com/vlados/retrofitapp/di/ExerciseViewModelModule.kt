package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.ExerciseRepository
import com.vlados.retrofitapp.ExerciseViewModel
import dagger.Module
import dagger.Provides

@Module
class ExerciseViewModelModule {
    @Provides
    fun provideExerciseViewModel(repository: ExerciseRepository): ExerciseViewModel{
        return ExerciseViewModel(repository)
    }
}