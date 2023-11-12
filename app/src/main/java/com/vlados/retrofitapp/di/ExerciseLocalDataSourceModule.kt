package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.ExerciseLocalDataSource
import dagger.Module
import dagger.Provides

@Module
object ExerciseLocalDataSourceModule {
    @Provides
    fun provideLocalDataSource(): ExerciseLocalDataSource {
        return ExerciseLocalDataSource()
    }
}