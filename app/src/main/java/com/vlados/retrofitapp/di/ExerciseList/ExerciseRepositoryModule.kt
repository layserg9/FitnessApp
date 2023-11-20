package com.vlados.retrofitapp.di.ExerciseList

import com.vlados.retrofitapp.data.local.ExerciseLocalDataSource
import com.vlados.retrofitapp.data.remote.ExerciseRemoteDataSource
import com.vlados.retrofitapp.data.ExerciseRepository
import dagger.Module
import dagger.Provides

@Module
object ExerciseRepositoryModule {
    @Provides
fun provideRepository(localDataSource: ExerciseLocalDataSource, remoteDataSource: ExerciseRemoteDataSource)
: ExerciseRepository {
return ExerciseRepository(localDataSource, remoteDataSource)
}
}