package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import javax.inject.Inject

class AddNewTaskUseCase @Inject constructor(
    private val tasksRepository: ITasksRepository
) {

    suspend operator fun invoke(task: Task){
        tasksRepository.addNewTask(task)
    }

}