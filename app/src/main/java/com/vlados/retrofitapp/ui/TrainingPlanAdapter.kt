package com.vlados.retrofitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vlados.retrofitapp.R

class TrainingPlanAdapter() :
    androidx.recyclerview.widget.ListAdapter<ListItem, RecyclerView.ViewHolder>(
        ExerciseComparator()
    ) {
    class ExerciseComparator : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private val EXERCISE_CONTENT = 1
        private val WEEKDAY_CONTENT = 2
        private val LOADER_CONTENT = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = currentList[position]) {
            is ListItem.ExerciseViewState -> EXERCISE_CONTENT
            is ListItem.WeekDaysViewState -> WEEKDAY_CONTENT
            else -> LOADER_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val exerciseView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        val weekdayView =
            LayoutInflater.from(parent.context).inflate(R.layout.weekday_item, parent, false)
        return when (viewType) {
            EXERCISE_CONTENT -> ExerciseViewHolder(exerciseView, {})
            else -> WeekdayViewHolder(weekdayView)
//            WEEKDAY_CONTENT -> WeekdayViewHolder(weekdayView)
//            else -> //TODO LoaderViewHolder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == EXERCISE_CONTENT) {
            val correctViewHolder: ExerciseViewHolder? =
                holder as? ExerciseViewHolder
            val elementOfList = currentList[position] as ListItem.ExerciseViewState
            correctViewHolder?.bind(elementOfList)
        } else if (viewType == WEEKDAY_CONTENT) {
            val correctViewHolder: WeekdayViewHolder? =
                holder as? WeekdayViewHolder
            val elementOfList = currentList[position] as ListItem.WeekDaysViewState
            correctViewHolder?.bind(elementOfList)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}