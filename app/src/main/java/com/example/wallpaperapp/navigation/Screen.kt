package com.example.wallpaperapp.navigation

import com.example.wallpaperapp.utils.Constants

sealed class Screen(val route: String) {
    object HOME: Screen(Constants.ROUTE_HOME)
}