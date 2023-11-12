package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.ExerciseListFragment
import com.vlados.retrofitapp.ExerciseViewModel
import dagger.Component

@Component(modules = [
    ExerciseViewModelModule::class,
    ExerciseRemoteDataSourceModule::class,
    ExerciseLocalDataSourceModule::class,
    ExerciseRepositoryModule::class,
    RemoteDataSourceModule::class])

interface AppComponent {
fun exerciseViewModel():ExerciseViewModel

    fun inject(fragment: ExerciseListFragment)
}