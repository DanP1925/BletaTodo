package com.danp1925.todolist.presentation.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp1925.todolist.domain.usecases.GetTaskUseCase
import com.danp1925.todolist.ui.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    private val taskId: Int = requireNotNull(savedStateHandle[NavRoutes.TaskDetailArgs.TaskId]) {
        "Task Id cannot be null"
    }

    private val _uiState = MutableStateFlow(TaskDetailScreenState())
    val uiState: StateFlow<TaskDetailScreenState> = _uiState

    init {
        viewModelScope.launch {
            val task = getTaskUseCase(taskId)
            _uiState.update {
                _uiState.value.copy(
                    title = task.title,
                    description = task.description
                )
            }
        }
    }

}