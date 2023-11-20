package com.vlados.retrofitapp.di.TrainingPlan

import com.vlados.retrofitapp.data.local.TrainingPlanLocalDataSource
import dagger.Module
import dagger.Provides

@Module
object TrainingPlanLocalDataSourceModule {
    @Provides
    fun provideLocalDataSource(): TrainingPlanLocalDataSource{
        return TrainingPlanLocalDataSource()
    }
}