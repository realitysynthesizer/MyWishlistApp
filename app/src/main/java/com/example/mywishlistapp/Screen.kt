package com.example.mywishlistapp

sealed class Screen(
    val route:String
) {
    object homescreen : Screen("home_screen")
    object addScreen: Screen("add_screen")
}