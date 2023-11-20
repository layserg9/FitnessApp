package com.vlados.retrofitapp.di.ExerciseList

import com.vlados.retrofitapp.data.remote.ExerciseRemoteDataSource
import com.vlados.retrofitapp.data.remote.RemoteDataSource
import com.vlados.retrofitapp.data.remote.retrofit.ExerciseApi
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