package com.wizeline.coroutinesexercises.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.wizeline.coroutinesexercises.ui.search.SearchViewModel.Companion.QUERY_SAVED_STATE_KEY
import com.wizeline.coroutinesexercises.ui.search.SearchViewModel.Companion.DEFAULT_QUERY
import timber.log.Timber

object MoviesDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search"
}

class MoviesNavigationActions(navController: NavHostController) {
    val navigateToSearch: (String?) -> Unit = { query ->
        Timber.d("Navigating to search screen")
        navController.navigate("${MoviesDestinations.SEARCH_ROUTE}?${QUERY_SAVED_STATE_KEY}=${query ?: DEFAULT_QUERY}") {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateUp: () -> Unit = { navController.navigateUp() }
}