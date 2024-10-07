package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val tasksRepository: ITasksRepository
){

    suspend operator fun invoke() : List<Task>{
        return tasksRepository.getTasks()
    }

}