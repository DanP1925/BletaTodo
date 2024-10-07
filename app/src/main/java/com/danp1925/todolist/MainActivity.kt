package com.danp1925.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danp1925.todolist.presentation.list.TasksScreens
import com.danp1925.todolist.presentation.list.model.Task
import com.danp1925.todolist.ui.theme.TODOListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOListTheme {
                TasksScreens(getFakeTasks())
            }
        }
    }


    fun getFakeTasks(): List<Task> = listOf(
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
