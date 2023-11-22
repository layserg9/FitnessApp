package com.vlados.retrofitapp.di.TrainingPlan

import com.vlados.retrofitapp.data.local.TrainingPlanLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TrainingPlanLocalDataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(): TrainingPlanLocalDataSource{
        return TrainingPlanLocalDataSource()
    }
}