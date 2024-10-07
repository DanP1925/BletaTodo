package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.data.TasksRepository
import com.danp1925.todolist.domain.models.Task
import javax.inject.Inject

class AddNewTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {

    suspend operator fun invoke(task: Task){
        return tasksRepository.addNewTask(task)
    }

}