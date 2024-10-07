package com.danp1925.todolist.presentation.list

import com.danp1925.todolist.presentation.list.model.Task

data class TasksScreenState(
    val tasks: List<Task> = emptyList()
)