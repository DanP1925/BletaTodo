package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import javax.inject.Inject

class UpdateTaskCompletionUseCase @Inject constructor(
    private val tasksRepository: ITasksRepository
) {

    suspend operator fun invoke(taskId: Int, isCompleted: Boolean): Boolean {
        val newValue = !isCompleted
        tasksRepository.updateTaskCompletion(taskId, newValue)
        return newValue
    }

}