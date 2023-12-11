package com.vlados.retrofitapp.di.TrainingPlan

import com.vlados.retrofitapp.data.ExerciseDao
import com.vlados.retrofitapp.data.ExerciseDataBase
import com.vlados.retrofitapp.data.local.TrainingPlanLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TrainingPlanLocalDataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(
        exerciseDao: ExerciseDao
    ): TrainingPlanLocalDataSource {
        return TrainingPlanLocalDataSource(exerciseDao)
    }
}