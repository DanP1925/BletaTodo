package com.danp1925.todolist.data

import com.danp1925.todolist.data.di.IoDispatcher
import com.danp1925.todolist.data.local.LocalTask
import com.danp1925.todolist.data.local.PartialLocalTask
import com.danp1925.todolist.data.local.TasksDao
import com.danp1925.todolist.domain.ITasksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.danp1925.todolist.domain.models.Task

class TasksRepository @Inject constructor(
    private val tasksDao: TasksDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ITasksRepository {

    override fun getTasks(): Flow<List<Task>> = tasksDao.getTasks().map {
        it.map { localTask -> localTask.toDomain() }
    }.flowOn(dispatcher)

    override suspend fun getTask(taskId: Int): Task = withContext(dispatcher) {
        return@withContext tasksDao.getTask(taskId)?.toDomain()
            ?: throw Exception("No task with that id found")
    }

    override suspend fun addNewTask(task: Task) = withContext(dispatcher) {
        tasksDao.insertTask(LocalTask.fromDomain(task))
    }

    override suspend fun updateTaskCompletion(taskId: Int, newCompletionValue: Boolean) =
        withContext(dispatcher) {
            tasksDao.updateCompletion(PartialLocalTask(taskId, newCompletionValue))
        }

    override suspend fun deleteTask(taskId: Int) = withContext(dispatcher) {
        val task = getTask(taskId)
        tasksDao.deleteTask(LocalTask.fromDomain(task))
    }

}