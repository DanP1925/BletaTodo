package com.danp1925.todolist.presentation.tasklist

import com.danp1925.todolist.presentation.tasklist.models.Task

data class TasksScreenState(
    val tasks: List<Task> = emptyList()
)