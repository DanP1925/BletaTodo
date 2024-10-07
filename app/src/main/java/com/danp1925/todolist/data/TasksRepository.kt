package com.danp1925.todolist.data

import com.danp1925.todolist.data.di.IoDispatcher
import com.danp1925.todolist.data.local.LocalTask
import com.danp1925.todolist.data.local.TasksDao
import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task as DomainTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksRepository @Inject constructor(
    private val tasksDao: TasksDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ITasksRepository {

    override suspend fun getTasks(): List<DomainTask> = withContext(dispatcher) {
        return@withContext getFakeTasks()
    }

    override suspend fun addNewTask(task: DomainTask) = withContext(dispatcher) {
        tasksDao.insertTask(LocalTask.fromDomain(task))
    }

    private fun getFakeTasks(): List<DomainTask> = listOf(
        DomainTask("Estudiar corutinas", "", true),
        DomainTask("Estudiar flow", "", false),
        DomainTask("Estudiar Jetpack Compose", "", true),
        DomainTask("Estudiar corutinas", "", true),
        DomainTask("Estudiar flow", "", false),
        DomainTask("Estudiar Jetpack Compose", "", true),
        DomainTask("Estudiar corutinas", "", true),
        DomainTask("Estudiar flow", "", false),
        DomainTask("Estudiar Jetpack Compose", "", true),
        DomainTask("Estudiar corutinas", "", true),
        DomainTask("Estudiar flow", "", false),
        DomainTask("Estudiar Jetpack Compose", "", true),
        DomainTask("Estudiar corutinas", "", true),
        DomainTask("Estudiar flow", "", false),
        DomainTask("Estudiar Jetpack Compose", "", true)
    )

}