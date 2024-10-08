package com.danp1925.todolist.domain

import com.danp1925.todolist.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface ITasksRepository {
    fun getTasks(): Flow<List<Task>>
    suspend fun getTask(taskId: Int): Task
    suspend fun addNewTask(task: Task)
    suspend fun updateTaskCompletion(taskId: Int, newCompletionValue: Boolean)
}