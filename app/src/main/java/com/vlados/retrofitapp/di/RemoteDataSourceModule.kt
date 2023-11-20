package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
object RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }
}