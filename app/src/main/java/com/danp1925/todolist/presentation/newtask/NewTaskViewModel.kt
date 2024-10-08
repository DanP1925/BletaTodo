package com.danp1925.todolist.presentation.newtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp1925.todolist.domain.models.Task
import com.danp1925.todolist.domain.usecases.AddNewTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewTaskScreenState())
    val uiState: StateFlow<NewTaskScreenState> = _uiState

    fun updateTitle(newTitle: String) {
        _uiState.update { _uiState.value.copy(taskTitle = newTitle) }
    }

    fun updateDescription(newDescription: String) {
        _uiState.update { _uiState.value.copy(taskDescription = newDescription) }
    }

    fun addNewTask() {
        viewModelScope.launch {
            addNewTaskUseCase(
                Task(
                    title = _uiState.value.taskTitle,
                    description = _uiState.value.taskDescription,
                    isCompleted = false
                )
            )
        }
    }

}