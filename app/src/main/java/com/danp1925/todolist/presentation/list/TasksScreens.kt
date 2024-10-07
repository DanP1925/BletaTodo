package com.danp1925.todolist.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danp1925.todolist.R
import com.danp1925.todolist.presentation.list.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreens(
    tasks: List<Task>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.tasks_screen_title))
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ){
                TasksList(tasks)
            }
        }
    )
}