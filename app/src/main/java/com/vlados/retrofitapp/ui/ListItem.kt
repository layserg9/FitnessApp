package com.vlados.retrofitapp.ui

sealed class ListItem() {
    class ExerciseViewState(
        val id: Int,
        val name: String,
        val description: String,
        val image: String?
    ) : ListItem()

    class WeekDaysViewState(val name: String) : ListItem()
}
