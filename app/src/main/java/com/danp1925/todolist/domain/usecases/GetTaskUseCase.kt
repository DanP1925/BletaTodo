package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val tasksRepository: ITasksRepository
){
    suspend operator fun invoke(taskId: Int) = tasksRepository.getTask(taskId)
}