package com.vlados.myownbottomnavigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vlados.retrofitapp.ExerciseViewHolder
import com.vlados.retrofitapp.R
import com.vlados.retrofitapp.Retrofit.Exercise

class ExerciseAdapter() : ListAdapter<Exercise, ExerciseViewHolder>(ExerciseComparator()) {

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
        val exercise: Exercise? = currentList.getOrNull(position)
        if(exercise != null){
        holder.bind(exercise)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}