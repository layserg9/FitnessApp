package com.vlados.retrofitapp.di.ExerciseList

import android.content.Context
import androidx.room.Room
import com.vlados.retrofitapp.data.ExerciseDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ExerciseDataBaseModule {
    @Singleton
    @Provides
    fun provideExerciseDataBase(context: Context): ExerciseDataBase{
        return Room.databaseBuilder(
            context,
            ExerciseDataBase::class.java,
            "exercise_database"
        ).build()
    }
}