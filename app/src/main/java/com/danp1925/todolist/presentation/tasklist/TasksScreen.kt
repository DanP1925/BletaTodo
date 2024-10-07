package com.danp1925.todolist.presentation.tasklist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danp1925.todolist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreens(
    viewModel: TasksViewModel = hiltViewModel(),
    onAddTaskClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.tasks_screen_title))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddTaskClick() },
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Task",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                TasksList(uiState.tasks)
            }
        }
    )
}