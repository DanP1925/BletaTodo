package com.danp1925.todolist.domain

import com.danp1925.todolist.domain.model.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(){

    operator fun invoke() : List<Task>{
        return getFakeTasks()
    }

    private fun getFakeTasks(): List<Task> = listOf(
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true),
        Task("Estudiar corutinas", true),
        Task("Estudiar flow", false),
        Task("Estudiar Jetpack Compose", true)
    )

}