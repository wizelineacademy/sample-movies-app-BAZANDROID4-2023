package com.wizeline.coroutinesexercises.ui

import androidx.compose.runtime.Composable
import com.wizeline.coroutinesexercises.ui.home.HomeRoute
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme

@Composable
fun MoviesApp() {
    MoviesTheme {
        HomeRoute()
    }
}