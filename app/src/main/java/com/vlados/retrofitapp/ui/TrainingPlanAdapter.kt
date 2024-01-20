package com.vlados.retrofitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vlados.retrofitapp.R
import com.vlados.retrofitapp.data.Weekdays

class TrainingPlanAdapter(
    private val deleteFromPlan: (Int, Weekdays) -> Unit
) :
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
        private const val EXERCISE_CONTENT = 1
        private const val WEEKDAY_CONTENT = 2
        private const val LOADER_CONTENT = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is ListItem.ExerciseViewState -> EXERCISE_CONTENT
            is ListItem.WeekDaysViewState -> WEEKDAY_CONTENT
            else -> LOADER_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            EXERCISE_CONTENT -> {
                val exerciseView = inflater.inflate(R.layout.exercise_item, parent, false)
                ExerciseViewHolder(exerciseView)
            }
            WEEKDAY_CONTENT -> {
                val weekdayView = inflater.inflate(R.layout.weekday_item, parent, false)
                WeekdayViewHolder(weekdayView)
            }
            else -> {
                val loaderView = inflater.inflate(R.layout.loader_item, parent, false)
                LoaderViewHolder(loaderView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            EXERCISE_CONTENT -> {
                val correctViewHolder: ExerciseViewHolder? = holder as? ExerciseViewHolder
                val elementOfList = currentList[position] as ListItem.ExerciseViewState
                correctViewHolder?.bindForTrainingPlanAdapter(elementOfList, deleteFromPlan)
            }
            WEEKDAY_CONTENT -> {
                val correctViewHolder: WeekdayViewHolder? = holder as? WeekdayViewHolder
                val elementOfList = currentList[position] as ListItem.WeekDaysViewState
                correctViewHolder?.bind(elementOfList)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}