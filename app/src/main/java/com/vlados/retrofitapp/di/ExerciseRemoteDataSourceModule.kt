package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.ExerciseRemoteDataSource
import com.vlados.retrofitapp.RemoteDataSource
import com.vlados.retrofitapp.Retrofit.ExerciseApi
import dagger.Module
import dagger.Provides

@Module
object ExerciseRemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSource(dataSource: RemoteDataSource): ExerciseRemoteDataSource {
        val exerciseApi = dataSource.getClient().create(ExerciseApi::class.java)
        return ExerciseRemoteDataSource(exerciseApi)
    }
}