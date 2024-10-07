package com.danp1925.todolist.data

import com.danp1925.todolist.data.di.IoDispatcher
import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ITasksRepository {

    override suspend fun getTasks(): List<Task> = withContext(dispatcher) {
        return@withContext getFakeTasks()
    }

    private fun getFakeTasks(): List<Task> = listOf(
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true)
    )

}