package com.danp1925.todolist.presentation.taskdetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danp1925.todolist.R
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    viewModel: TaskDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect{ event ->
            when(event){
                is TaskDetailEvents.OnDeleteCompleted -> onBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.task_detail_screen_title))
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (uiState.isLoading){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                } else if (uiState.showAlertDialog) {
                    DeleteDialog(
                        onConfirmRequest = viewModel::deleteTask,
                        onDismissRequest = viewModel::hideDeleteDialog
                    )
                } else {
                    TaskDetailContent(
                        title = uiState.title,
                        description = uiState.description,
                        isCompleted = uiState.isCompleted,
                        onDeleteButtonClicked = viewModel::showDeleteDialog,
                        onCompleteButtonClicked = viewModel::updateCompletionStatus
                    )
                }
            }
        }
    )
}

@Composable
fun TaskDetailContent(
    title: String,
    description: String,
    isCompleted: Boolean,
    onDeleteButtonClicked: () -> Unit,
    onCompleteButtonClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = if (isCompleted) {
                stringResource(R.string.task_detail_screen_completed)
            } else {
                stringResource(R.string.task_detail_screen_not_completed)
            },
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = if (isCompleted) Color.Green else Color.Red,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp)
        ) {
            Button(
                onClick = onDeleteButtonClicked,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(stringResource(R.string.task_detail_screen_button_delete))
            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Button(
                onClick = onCompleteButtonClicked,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(stringResource(R.string.task_detail_screen_button_complete))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(
    onConfirmRequest: () -> Unit,
    onDismissRequest : () -> Unit
){
    BasicAlertDialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.task_detail_screen_dialog_title),
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(
                        onClick = onDismissRequest,
                    ) {
                        Text(stringResource(R.string.task_detail_screen_dialog_cancel))
                    }
                    TextButton(
                        onClick =  onConfirmRequest,
                    ) {
                        Text(stringResource(R.string.task_detail_screen_dialog_confirm))
                    }
                }
            }
        }
    }
}