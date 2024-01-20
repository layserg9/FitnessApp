package com.vlados.retrofitapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeekDaysMenuWidget(
    weekdays: Array<String>,
    trainingDay: MutableState<String>
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Column {
        Box {
            TextField(
                value = trainingDay.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isExpanded,
                        onIconClick = { isExpanded = true }
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .clickable {
                        isExpanded = true
                    },
                enabled = false
            )
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                weekdays.forEach { weekday ->
                    DropdownMenuItem(
                        content = {
                            Text(text = weekday)
                        },
                        onClick = {
                            trainingDay.value = weekday
                            isExpanded = false
                        })
                }
            }
        }
    }
}