package com.vlados.retrofitapp.di.BottomSheet

import com.vlados.retrofitapp.data.ExerciseRepository
import com.vlados.retrofitapp.data.TrainingPlanRepository
import com.vlados.retrofitapp.ui.BottomSheetViewModel
import dagger.Module
import dagger.Provides

@Module
class BottomSheetViewModelModule {
    @Provides
    fun provideBottomSheetViewModel(
        exerciseListRepository: ExerciseRepository,
        trainingPlanRepository: TrainingPlanRepository
    ): BottomSheetViewModel {
        return BottomSheetViewModel(exerciseListRepository, trainingPlanRepository)
    }
}
