package com.wizeline.coroutinesexercises.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wizeline.coroutinesexercises.ui.home.HomeRoute
import com.wizeline.coroutinesexercises.ui.home.HomeViewModel
import com.wizeline.coroutinesexercises.ui.search.SearchRoute
import com.wizeline.coroutinesexercises.ui.search.SearchViewModel
import com.wizeline.coroutinesexercises.ui.search.SearchViewModel.Companion.QUERY_SAVED_STATE_KEY
import com.wizeline.coroutinesexercises.ui.search.SearchViewModel.Companion.DEFAULT_QUERY

@Composable
fun MoviesNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MoviesDestinations.HOME_ROUTE,
) {
    val navigationActions = remember(navController) {
        MoviesNavigationActions(navController)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(MoviesDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeRoute(
                viewModel = homeViewModel,
                navigateToSearch = { navigationActions.navigateToSearch(null) },
            )
        }
        composable(
            "${MoviesDestinations.SEARCH_ROUTE}?${QUERY_SAVED_STATE_KEY}={${QUERY_SAVED_STATE_KEY}}",
            arguments = listOf(
                navArgument(QUERY_SAVED_STATE_KEY) { defaultValue = DEFAULT_QUERY }
            )
        ) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchRoute(
                viewModel = searchViewModel,
                navigateUp = navigationActions.navigateUp
            )
        }
    }
}