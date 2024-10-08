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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private enum class SortType {
    ALPHABETICALLY, ID
}

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private val _shouldShowMenu = MutableStateFlow(false)
    private val _sortType = MutableStateFlow(SortType.ID)
    private val _tasks = getTasksUseCase().distinctUntilChanged().map { tasks ->
        tasks.map { it.toUI() }
    }
    val uiState: StateFlow<TasksScreenState> =
        combine(_tasks, _shouldShowMenu, _sortType) { uiTasks, shouldShowMenu, sortType ->
            TasksScreenState(
                tasks = if (sortType == SortType.ID) {
                    uiTasks.sortedBy { it.id }
                } else {
                    uiTasks.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.title }))
                },
                shouldShowMenu = shouldShowMenu
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TasksScreenState(isLoading = true)
        )

    fun showMenu() {
        _shouldShowMenu.update { true }
    }

    fun hideMenu() {
        _shouldShowMenu.update { false }
    }

    fun sortTasksById() {
        _sortType.update { SortType.ID }
        _shouldShowMenu.update { false }
    }

    fun sortTasksAlphabetically() {
        _sortType.update { SortType.ALPHABETICALLY }
        _shouldShowMenu.update { false }
    }

}