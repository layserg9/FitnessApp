package com.vlados.retrofitapp.di.TrainingPlan

import com.vlados.retrofitapp.data.TrainingPlanRepository
import com.vlados.retrofitapp.data.local.TrainingPlanLocalDataSource
import dagger.Module
import dagger.Provides

@Module
object TrainingPlanRepositoryModule{
    @Provides
    fun provideRepository(localDataSource: TrainingPlanLocalDataSource): TrainingPlanRepository{
        return TrainingPlanRepository(localDataSource)
    }
}