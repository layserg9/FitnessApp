package com.vlados.retrofitapp.di.ExerciseList

import com.vlados.retrofitapp.data.local.ExerciseLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ExerciseLocalDataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(): ExerciseLocalDataSource {
        return ExerciseLocalDataSource()
    }
}