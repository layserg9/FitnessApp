package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.di.BottomSheet.BottomSheetViewModelModule
import com.vlados.retrofitapp.di.ExerciseList.ExerciseLocalDataSourceModule
import com.vlados.retrofitapp.di.ExerciseList.ExerciseRemoteDataSourceModule
import com.vlados.retrofitapp.di.ExerciseList.ExerciseRepositoryModule
import com.vlados.retrofitapp.di.ExerciseList.ExerciseViewModelModule
import com.vlados.retrofitapp.di.TrainingPlan.TrainingPlanLocalDataSourceModule
import com.vlados.retrofitapp.di.TrainingPlan.TrainingPlanRepositoryModule
import com.vlados.retrofitapp.ui.BottomSheetFragment
import com.vlados.retrofitapp.ui.BottomSheetViewModel
import com.vlados.retrofitapp.ui.ExerciseListFragment
import com.vlados.retrofitapp.ui.ExerciseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ExerciseViewModelModule::class,
        ExerciseRemoteDataSourceModule::class,
        ExerciseLocalDataSourceModule::class,
        ExerciseRepositoryModule::class,
        BottomSheetViewModelModule::class,
        RemoteDataSourceModule::class,
        TrainingPlanLocalDataSourceModule::class,
        TrainingPlanRepositoryModule::class]
)

interface AppComponent {
    fun bottomSheetViewModel(): BottomSheetViewModel
    fun exerciseViewModel(): ExerciseViewModel

    fun inject(fragment: ExerciseListFragment)
    fun inject(fragment: BottomSheetFragment)
}