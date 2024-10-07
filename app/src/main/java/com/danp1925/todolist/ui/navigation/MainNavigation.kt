package com.danp1925.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danp1925.todolist.presentation.newtask.NewTaskScreen
import com.danp1925.todolist.presentation.tasklist.TasksScreens

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavRoutes.TasksList) {
        addTasksList(navController)
        addNewTask(navController)
    }

}

private fun NavGraphBuilder.addTasksList(navController: NavHostController) {

    composable(NavRoutes.TasksList) {
        TasksScreens(onAddTaskClick = { navController.navigate(NavRoutes.NewTask) })
    }

}

private fun NavGraphBuilder.addNewTask(navController: NavHostController) {

    composable(NavRoutes.NewTask) {
        NewTaskScreen()
    }

}