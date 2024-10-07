package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val tasksRepository: ITasksRepository
){

    operator fun invoke() : Flow<List<Task>> = tasksRepository.getTasks()

}