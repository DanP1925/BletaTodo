package com.danp1925.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danp1925.todolist.presentation.newtask.NewTaskScreen
import com.danp1925.todolist.presentation.taskdetail.TaskDetailScreen
import com.danp1925.todolist.presentation.tasklist.TasksScreens

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavRoutes.TasksList) {
        addTasksList(navController)
        addNewTask(navController)
        addTaskDetail(navController)
    }

}

private fun NavGraphBuilder.addTasksList(navController: NavHostController) {

    composable(NavRoutes.TasksList) {
        TasksScreens(
            onAddTaskClicked = { navController.navigate(NavRoutes.NewTask) },
            onTaskItemClicked = { navController.navigate(NavRoutes.TaskDetail) }
        )
    }

}

private fun NavGraphBuilder.addNewTask(navController: NavHostController) {

    composable(NavRoutes.NewTask) {
        NewTaskScreen(onButtonClicked = { navController.popBackStack() })
    }

}

private fun NavGraphBuilder.addTaskDetail(navController: NavHostController) {

    composable(NavRoutes.TaskDetail) {
        TaskDetailScreen()
    }

}