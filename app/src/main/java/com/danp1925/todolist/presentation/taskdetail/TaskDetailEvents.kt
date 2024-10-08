package com.danp1925.todolist.presentation.taskdetail

sealed interface TaskDetailEvents {
    data object OnDeleteCompleted : TaskDetailEvents
    data class OnExceptionThrown(val message: String) : TaskDetailEvents
}