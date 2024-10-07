package com.danp1925.todolist.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danp1925.todolist.presentation.list.model.Task

@Composable
fun TasksList(
    tasks: List<Task>
) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        items(tasks) { task ->
            TasksItem(task)
        }
    }
}