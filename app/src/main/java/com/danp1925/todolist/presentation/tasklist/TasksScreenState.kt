package com.danp1925.todolist.presentation.tasklist

import com.danp1925.todolist.presentation.tasklist.models.UITask

data class TasksScreenState(
    val tasks: List<UITask> = emptyList()
)