package com.danp1925.todolist.presentation.list.model

import com.danp1925.todolist.domain.model.Task as DomainTask

data class Task(
    val title: String,
    val isCompleted: Boolean
)

fun DomainTask.toUI() = Task(
    title = title,
    isCompleted = isCompleted
)