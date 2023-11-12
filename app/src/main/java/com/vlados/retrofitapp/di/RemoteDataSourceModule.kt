package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
object RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource{
        return RemoteDataSource()
    }
}