package com.danp1925.todolist.presentation.newtask

sealed interface NewTaskEvents {
    data object OnCreateCompleted : NewTaskEvents
}