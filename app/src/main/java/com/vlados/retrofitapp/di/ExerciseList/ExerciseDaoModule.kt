package com.vlados.retrofitapp.di.ExerciseList

import com.vlados.retrofitapp.data.ExerciseDao
import com.vlados.retrofitapp.data.ExerciseDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ExerciseDaoModule {

    @Singleton
    @Provides
    fun provideExerciseDao(
        exerciseDataBase: ExerciseDataBase
    ): ExerciseDao {
        return exerciseDataBase.getExerciseDao()
    }
}