package com.vlados.retrofitapp.di

import android.content.Context
import com.vlados.retrofitapp.data.ExerciseDataBase
import com.vlados.retrofitapp.di.BottomSheet.BottomSheetViewModelModule
import com.vlados.retrofitapp.di.ExerciseList.*
import com.vlados.retrofitapp.di.TrainingPlan.TrainingPlanLocalDataSourceModule
import com.vlados.retrofitapp.di.TrainingPlan.TrainingPlanRepositoryModule
import com.vlados.retrofitapp.di.TrainingPlan.TrainingPlanViewModelModule
import com.vlados.retrofitapp.ui.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ExerciseViewModelModule::class,
        ExerciseRemoteDataSourceModule::class,
        ExerciseLocalDataSourceModule::class,
        ExerciseRepositoryModule::class,
        ExerciseDataBaseModule::class,
        ExerciseDaoModule::class,
        BottomSheetViewModelModule::class,
        RemoteDataSourceModule::class,
        TrainingPlanLocalDataSourceModule::class,
        TrainingPlanRepositoryModule::class,
        TrainingPlanViewModelModule::class]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun bottomSheetViewModel(): AddExerciseToPlanBottomSheetViewModel
    fun exerciseViewModel(): ExerciseViewModel
    fun trainingPlanViewModel(): TrainingPlanViewModel
    fun inject(fragment: ExerciseListFragment)
    fun inject(fragment: AddExerciseToPlanBottomSheetFragment)
    fun inject(fragment: TrainingPlanFragment)
}