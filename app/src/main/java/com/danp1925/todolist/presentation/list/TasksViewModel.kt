package com.danp1925.todolist.presentation.list

import androidx.lifecycle.ViewModel
import com.danp1925.todolist.presentation.list.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TasksViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TasksScreenState())
    val uiState: StateFlow<TasksScreenState> = _uiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _uiState.update {
            _uiState.value.copy(tasks = getFakeTasks())
        }
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