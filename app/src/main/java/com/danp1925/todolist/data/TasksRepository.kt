package com.danp1925.todolist.data

import com.danp1925.todolist.data.di.IoDispatcher
import com.danp1925.todolist.data.local.LocalTask
import com.danp1925.todolist.data.local.TasksDao
import com.danp1925.todolist.domain.ITasksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.danp1925.todolist.domain.models.Task as DomainTask

class TasksRepository @Inject constructor(
    private val tasksDao: TasksDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ITasksRepository {

    override fun getTasks(): Flow<List<DomainTask>> = tasksDao.getTasks().map {
        it.map { localTask -> localTask.toDomain() }
    }.flowOn(dispatcher)

    override suspend fun addNewTask(task: DomainTask) = withContext(dispatcher) {
        tasksDao.insertTask(LocalTask.fromDomain(task))
    }

}