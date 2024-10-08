package com.danp1925.todolist.presentation.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp1925.todolist.domain.usecases.GetTasksUseCase
import com.danp1925.todolist.presentation.tasklist.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private val _tasks = getTasksUseCase().distinctUntilChanged().map { tasks ->
        tasks.map { it.toUI() }
    }
    val uiState: StateFlow<TasksScreenState> = combine(_tasks) { uiTasks ->
        TasksScreenState(tasks = uiTasks.first())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TasksScreenState()
    )

}