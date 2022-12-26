package com.wizeline.coroutinesexercises.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.wizeline.coroutinesexercises.ui.navigation.MoviesNavGraph
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme

@Composable
fun MoviesApp() {
    MoviesTheme {
        val navController = rememberNavController()
        MoviesNavGraph(navController = navController)
    }
}