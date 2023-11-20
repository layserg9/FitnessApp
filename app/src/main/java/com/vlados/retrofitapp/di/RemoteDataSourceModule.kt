package com.vlados.retrofitapp.di

import com.vlados.retrofitapp.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }
}