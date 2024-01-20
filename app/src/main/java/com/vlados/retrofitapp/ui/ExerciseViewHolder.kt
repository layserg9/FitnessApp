package com.vlados.retrofitapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vlados.retrofitapp.R
import com.vlados.retrofitapp.data.Weekdays
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import com.vlados.retrofitapp.databinding.ExerciseItemBinding

class ExerciseViewHolder(
    item: View,
) :
    RecyclerView.ViewHolder(item) {
    private val viewBinding = ExerciseItemBinding.bind(item)

    fun bindForExerciseAdapter(exercise: Exercise, onExerciseClick: (Int) -> Unit) {
        viewBinding.nameOfExercise.text = exercise.name
        viewBinding.descriptionOfExercise.text = exercise.description
        itemView.setOnClickListener { onExerciseClick(exercise.id) }
        val image = exercise.images.getOrNull(0)?.image
        if (image != null) {
            Glide.with(itemView.context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(viewBinding.imageViewExerciseItem)
        } else {
            Glide.with(itemView.context)
                .load(R.drawable.no_exercise_image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(viewBinding.imageViewExerciseItem)
        }
        viewBinding.imageButton.setImageResource(R.drawable.add_exercise)
        viewBinding.imageButton.setOnClickListener { onExerciseClick(exercise.id) }

    }

    fun bindForTrainingPlanAdapter(
        exercise: ListItem.ExerciseViewState,
        onExerciseClick: (Int, Weekdays) -> Unit
    ) {
        viewBinding.nameOfExercise.text = exercise.name
        viewBinding.descriptionOfExercise.text = exercise.description
        val image = exercise.image
        if (image != null) {
            Glide.with(itemView.context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(viewBinding.imageViewExerciseItem)
        } else {
            Glide.with(itemView.context)
                .load(R.drawable.no_exercise_image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(viewBinding.imageViewExerciseItem)
        }
        viewBinding.imageButton.setImageResource(R.drawable.delete_exercise)
        viewBinding.imageButton.setOnClickListener {
            onExerciseClick(
                exercise.id,
                exercise.weekday
            )
        }
    }
}