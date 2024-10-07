package com.danp1925.todolist.domain

import com.danp1925.todolist.domain.models.Task

interface ITasksRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addNewTask(task: Task)
}