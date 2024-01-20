package com.vlados.retrofitapp.data

import androidx.room.*

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(exercises: List<ExerciseEntity>)

    @Delete
    fun deleteItems(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercise_entity_table")
    fun getAllItems(): List<ExerciseEntity>
}