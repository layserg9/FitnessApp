package com.vlados.retrofitapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vlados.retrofitapp.R
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import com.vlados.retrofitapp.databinding.ExerciseItemBinding

class ExerciseViewHolder(item: View, private val addToPlan: (Int) -> Unit) :
    RecyclerView.ViewHolder(item) {
    private val baseUrl = "https://wger.de"
    private val viewBinding = ExerciseItemBinding.bind(item)

    fun bind(exercise: Exercise) {
        viewBinding.nameOfExercise.text = exercise.name
        viewBinding.descriptionOfExercise.text = exercise.description
        itemView.setOnClickListener { addToPlan(exercise.id) }
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
    }

    fun bind(exercise: ListItem.ExerciseViewState) {
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
    }
}