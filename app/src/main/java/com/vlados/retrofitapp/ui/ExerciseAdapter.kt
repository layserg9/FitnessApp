package com.vlados.retrofitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vlados.retrofitapp.R
import com.vlados.retrofitapp.data.remote.retrofit.Exercise

class ExerciseAdapter(
    private val addExercise: (Int) -> Unit
) :
    ListAdapter<Exercise, ExerciseViewHolder>(ExerciseComparator()) {

    class ExerciseComparator : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val exerciseView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(exerciseView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise: Exercise = currentList.getOrNull(position) ?: return
        holder.bindForExerciseAdapter(exercise, addExercise)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}