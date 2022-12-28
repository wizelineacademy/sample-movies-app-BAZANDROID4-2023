package com.wizeline.coroutinesexercises.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    navigateToSearch: () -> Unit
) {
    val uiState by viewModel.uiState.subscribeAsState(initial = HomeUiState())
    HomeScreen(
        uiState = uiState,
        navigateToSearch = navigateToSearch
    )
}