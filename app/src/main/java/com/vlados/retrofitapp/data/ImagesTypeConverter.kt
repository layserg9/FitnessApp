package com.vlados.retrofitapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vlados.retrofitapp.data.remote.retrofit.ExerciseImage

class ImagesTypeConverter {
    @TypeConverter
    fun fromImages(images: List<ExerciseImage>): String {
        return Gson().toJson(images)
    }

    @TypeConverter
    fun toImages(imageString: String?): List<ExerciseImage> {
        val type = object : TypeToken<List<ExerciseImage>>() {}.type
        return Gson().fromJson(imageString, type)
    }
}