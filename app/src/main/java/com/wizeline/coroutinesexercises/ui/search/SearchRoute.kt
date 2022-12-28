package com.wizeline.coroutinesexercises.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState

@Composable
fun SearchRoute(
    viewModel: SearchViewModel,
    navigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.subscribeAsState(initial = SearchUiState())
    SearchScreen(
        uiState = uiState,
        onQueryChanged = { viewModel.setQuery(it) },
        onClear = { viewModel.clearQuery() },
        navigateUp = navigateUp
    )
}