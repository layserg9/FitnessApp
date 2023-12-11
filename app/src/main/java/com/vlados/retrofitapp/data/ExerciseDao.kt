package com.vlados.retrofitapp.data

import androidx.room.*

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(exercises: List<ExerciseEntity>)

    @Update
    fun updateItem(exerciseEntity: ExerciseEntity)

    @Delete
    fun deleteItem(exerciseEntity: ExerciseEntity)

    @Query("SELECT * FROM exercise_entity_table")
    fun getAllItems(): List<ExerciseEntity>
}