package com.danp1925.todolist.presentation.taskdetail

data class TaskDetailScreenState(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false
)