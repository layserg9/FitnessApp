package com.vlados.retrofitapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_entity_table")
data class ExerciseEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val description: String,
    val images: String?,
    val weekDay: Int
)
