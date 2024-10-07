package com.danp1925.todolist.presentation.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danp1925.todolist.R
import com.danp1925.todolist.presentation.list.model.Task

@Composable
fun TasksItem(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task.title,
            fontSize = 16.sp,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
        if (task.isCompleted) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = stringResource(R.string.tasks_item_check_description),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}