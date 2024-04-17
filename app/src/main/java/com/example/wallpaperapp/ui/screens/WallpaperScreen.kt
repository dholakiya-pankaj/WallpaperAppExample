package com.example.wallpaperapp.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.wallpaperapp.database.entity.WallpaperEntity

@Composable
fun WallpaperScreen(
    viewModel: WallpaperViewModel,
    wallpapers: LazyPagingItems<WallpaperEntity>
) {
    Scaffold(
        topBar = {
            MyToolbar(
                title = "Wallpapers",
                onBackButtonClicked = {},
                onRightButtonClicked = {})
        }
    ) { paddingValues ->
        WallpaperListContent(
            viewModel = viewModel,
            paddingValues = paddingValues,
            wallpapers = wallpapers
        )
    }
}