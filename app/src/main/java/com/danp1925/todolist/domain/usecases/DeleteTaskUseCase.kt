package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val tasksRepository: ITasksRepository
){

    suspend operator fun invoke(taskId: Int){
        tasksRepository.deleteTask(taskId)
    }

}