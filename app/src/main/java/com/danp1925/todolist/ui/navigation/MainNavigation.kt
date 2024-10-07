package com.danp1925.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danp1925.todolist.presentation.list.TasksScreens

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavRoutes.TasksList) {
        addTasksList(navController)
    }

}

private fun NavGraphBuilder.addTasksList(navController: NavHostController){

    composable(NavRoutes.TasksList) {
        TasksScreens()
    }

}