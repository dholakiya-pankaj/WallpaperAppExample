package com.example.wallpaperapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpaperapp.ui.screens.WallpaperScreen
import com.example.wallpaperapp.ui.screens.WallpaperViewModel
import com.example.wallpaperapp.utils.showToast

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route
    ) {
        homeRoute()
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.HOME.route) {
        val viewModel: WallpaperViewModel = hiltViewModel()
        val wallpapers = viewModel.getWallpapers().collectAsLazyPagingItems()
        val context = LocalContext.current
        val errorMessage by viewModel.errorMessage.collectAsState()

        LaunchedEffect(key1 = errorMessage) {
            context.showToast(
                message = errorMessage
            )
        }

        WallpaperScreen(
            viewModel = viewModel,
            wallpapers = wallpapers,
        )
    }
}