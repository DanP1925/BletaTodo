package com.danp1925.todolist.presentation.tasklist.models

import com.danp1925.todolist.domain.models.Task

data class UITask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

fun Task.toUI() = UITask(
    id = id!!,
    title = title,
    isCompleted = isCompleted
)