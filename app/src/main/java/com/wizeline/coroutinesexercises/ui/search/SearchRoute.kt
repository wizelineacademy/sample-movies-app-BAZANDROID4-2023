package com.wizeline.coroutinesexercises.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun SearchRoute(
    viewModel: SearchViewModel,
    navigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchScreen(
        uiState = uiState,
        onQueryChanged = { viewModel.setQuery(it) },
        onClear = { viewModel.clearQuery() },
        navigateUp = navigateUp
    )
}