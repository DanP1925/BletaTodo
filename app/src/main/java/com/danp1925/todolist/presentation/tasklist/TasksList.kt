package com.danp1925.todolist.presentation.tasklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.danp1925.todolist.presentation.tasklist.models.UITask

@Composable
fun TasksList(
    tasks: List<UITask>,
    onTaskItemClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).testTag("TaskList"),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(tasks) { task ->
            TasksItem(task = task, modifier = Modifier.clickable { onTaskItemClicked(task.id) })
        }
    }
}