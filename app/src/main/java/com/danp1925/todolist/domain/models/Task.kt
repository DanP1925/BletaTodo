package com.danp1925.todolist.domain.models

data class Task(
    val title: String,
    val description: String,
    val isCompleted: Boolean
)