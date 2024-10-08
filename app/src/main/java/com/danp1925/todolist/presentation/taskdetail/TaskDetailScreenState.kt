package com.danp1925.todolist.presentation.taskdetail

data class TaskDetailScreenState(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val showAlertDialog: Boolean = false,
    val isLoading: Boolean = false
)