package com.vlados.retrofitapp.di.TrainingPlan

import com.vlados.retrofitapp.data.ExerciseRepository
import com.vlados.retrofitapp.data.TrainingPlanRepository
import com.vlados.retrofitapp.ui.TrainingPlanViewModel
import dagger.Module
import dagger.Provides

@Module
class TrainingPlanViewModelModule {
    @Provides
    fun provideTrainingPlanViewModel(
        trainingPlanRepository: TrainingPlanRepository,
        exerciseRepository: ExerciseRepository
    ): TrainingPlanViewModel {
        return TrainingPlanViewModel(trainingPlanRepository, exerciseRepository)
    }
}