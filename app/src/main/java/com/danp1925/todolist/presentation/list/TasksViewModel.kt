package com.danp1925.todolist.presentation.list

import androidx.lifecycle.ViewModel
import com.danp1925.todolist.domain.GetTasksUseCase
import com.danp1925.todolist.presentation.list.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasksScreenState())
    val uiState: StateFlow<TasksScreenState> = _uiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        val tasks = getTasksUseCase().map { it.toUI() }
        _uiState.update {
            _uiState.value.copy(tasks = tasks)
        }
    }

}