package com.danp1925.todolist.presentation.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp1925.todolist.domain.usecases.GetTasksUseCase
import com.danp1925.todolist.presentation.tasklist.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private val _tasks = getTasksUseCase().distinctUntilChanged()
    val uiState: StateFlow<TasksScreenState> = combine(_tasks) { tasks ->
        TasksScreenState(tasks.first().map { it.toUI() })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TasksScreenState()
    )

}