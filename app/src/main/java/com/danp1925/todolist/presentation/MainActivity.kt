package com.danp1925.todolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.danp1925.todolist.ui.navigation.MainNavigation
import com.danp1925.todolist.ui.theme.TODOListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOListTheme {
                val navHostController = rememberNavController()
                MainNavigation(navController = navHostController)
            }
        }
    }

}
