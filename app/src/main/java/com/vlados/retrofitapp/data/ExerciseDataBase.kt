package com.vlados.retrofitapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ExerciseEntity::class],
    version = 1
)
@TypeConverters(ImagesTypeConverter:: class)

abstract class ExerciseDataBase : RoomDatabase() {
    abstract fun getExerciseDao(): ExerciseDao
}
