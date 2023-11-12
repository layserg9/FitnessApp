package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.ExerciseLocalDataSource
import com.vlados.retrofitapp.ExerciseRemoteDataSource
import com.vlados.retrofitapp.ExerciseRepository
import dagger.Module
import dagger.Provides

@Module
object ExerciseRepositoryModule {
    @Provides
fun provideRepository(localDataSource: ExerciseLocalDataSource, remoteDataSource: ExerciseRemoteDataSource)
: ExerciseRepository{
return ExerciseRepository(localDataSource, remoteDataSource)
}
}