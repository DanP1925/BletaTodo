package com.danp1925.todolist.presentation.newtask

data class NewTaskScreenState(
    val taskTitle: String = "",
    val taskDescription : String = "",
    val isLoading : Boolean = false
)