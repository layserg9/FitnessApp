package com.vlados.retrofitapp.ui

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddExerciseToPlanComposeBottomSheet(
    weekdays: Array<String>,
    dismiss: () -> Unit,
    trainingDay: MutableState<String>
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Выбери тренировочный день",
                fontSize = 20.sp
            )
            IconButton(
                onClick = dismiss,
                content = {
                    Image(
                        painterResource(id = R.drawable.ic_delete),
                        contentDescription = "close"
                    )
                }
            )
        }
        Spacer(modifier = Modifier.size(25.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            WeekDaysMenuWidget(weekdays, trainingDay)
        }
    }
}