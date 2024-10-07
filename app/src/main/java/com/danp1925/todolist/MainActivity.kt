package com.danp1925.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.danp1925.todolist.presentation.list.TasksScreens
import com.danp1925.todolist.presentation.list.TasksViewModel
import com.danp1925.todolist.ui.theme.TODOListTheme

class MainActivity : ComponentActivity() {

    private val tasksViewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOListTheme {
                TasksScreens(tasksViewModel)
            }
        }
    }

}
