package com.vlados.retrofitapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vlados.retrofitapp.databinding.WeekdayItemBinding


class WeekdayViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val viewBinding = WeekdayItemBinding.bind(item)

    fun bind(day: ListItem.WeekDaysViewState) {
        viewBinding.nameOfTheWeekday.text = day.name
    }
}