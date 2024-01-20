package com.vlados.retrofitapp.ui

import com.vlados.retrofitapp.data.Weekdays

sealed class ListItem() {
    class ExerciseViewState(
        val id: Int,
        val name: String,
        val description: String,
        val image: String?,
        val weekday: Weekdays
    ) : ListItem()

    class WeekDaysViewState(val name: String) : ListItem()
}
