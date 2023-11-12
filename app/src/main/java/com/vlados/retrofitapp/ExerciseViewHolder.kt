package com.vlados.retrofitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vlados.retrofitapp.Retrofit.Exercise
import com.vlados.retrofitapp.databinding.ExerciseItemBinding

class ExerciseViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val baseUrl = "https://wger.de"
    private val viewBinding = ExerciseItemBinding.bind(item)
    private val nameOfExercise = viewBinding.nameOfExercise
    private val descriptionOfExercise = viewBinding.descriptionOfExercise
    private val imageOfExercise = viewBinding.imageViewExerciseItem

    fun bind(exercise: Exercise) {
        nameOfExercise.text = exercise.name
        descriptionOfExercise.text = exercise.description
        val image = exercise.images.getOrNull(0)?.image
        if (image != null) {
            Glide.with(itemView.context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageOfExercise)
        }
    }
}